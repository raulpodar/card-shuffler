#!/bin/bash

# === Configuration ===
OLD_PKG_PREFIX="com.skydoves.pokedex"
NEW_PKG_PREFIX="com.raulp.cardshuffler"
# === End Configuration ===

# Exit on any error
set -e

echo "IMPORTANT: Ensure you have backed up your project or committed all changes to Git before proceeding."
read -p "Are you sure you want to continue? (yes/no): " confirmation
if [[ "$confirmation" != "yes" ]]; then
    echo "Aborted by user."
    exit 1
fi

# Convert package prefixes to path segments
# Example: com.skydoves.pokedex -> com/skydoves/pokedex
OLD_FULL_PATH_SEGMENT=$(echo "$OLD_PKG_PREFIX" | sed 's/\./\//g')
NEW_FULL_PATH_SEGMENT=$(echo "$NEW_PKG_PREFIX" | sed 's/\./\//g')

echo "Starting package renaming process..."
echo "Old Prefix: $OLD_PKG_PREFIX"
echo "New Prefix: $NEW_PKG_PREFIX"
echo "Old Path Segment: $OLD_FULL_PATH_SEGMENT"
echo "New Path Segment: $NEW_FULL_PATH_SEGMENT"
echo "-------------------------------------------"

# --- Phase 1: Rename package/import statements and other references in files ---
echo "Phase 1: Updating file contents..."

# Find all relevant files, excluding build, .gradle, and .git directories.
# Process .kt, .java, .xml, and .gradle.kts files.
find . -type d \( -path "./build" -o -path "./.gradle" -o -path "./.git" \) -prune -o \
       -type f \( -name "*.kt" -o -name "*.java" -o -name "*.xml" -o -name "*.gradle.kts" \) \
       -print0 | while IFS= read -r -d $'\0' file_path; do

    # Further filter to ensure we are in src directories or processing build/settings files
    if [[ "$file_path" == *"src/"* || "$file_path" == *"/build.gradle.kts"* || "$file_path" == *"/settings.gradle.kts"* ]]; then
        echo "Processing file: $file_path"

        # Perform the general package prefix replacement
        # Using a temp file for sed portability (especially for macOS compatibility)
        sed "s/${OLD_PKG_PREFIX//./\\.}/${NEW_PKG_PREFIX}/g" "$file_path" > "$file_path.tmp" && mv "$file_path.tmp" "$file_path"

        # Specific replacements for build.gradle.kts if it's the current file
        if [[ "$file_path" == *"/build.gradle.kts"* ]]; then
            # Namespace (often uses the full package prefix)
            sed "s/namespace = \"${OLD_PKG_PREFIX//./\\.}\"/namespace = \"${NEW_PKG_PREFIX}\"/g" "$file_path" > "$file_path.tmp" && mv "$file_path.tmp" "$file_path"
            sed "s/namespace=\"${OLD_PKG_PREFIX//./\\.}\"/namespace=\"${NEW_PKG_PREFIX}\"/g" "$file_path" > "$file_path.tmp" && mv "$file_path.tmp" "$file_path" # For different spacing

            # ApplicationId (if it matches the old package prefix)
            sed "s/applicationId = \"${OLD_PKG_PREFIX//./\\.}\"/applicationId = \"${NEW_PKG_PREFIX}\"/g" "$file_path" > "$file_path.tmp" && mv "$file_path.tmp" "$file_path"
            sed "s/applicationId=\"${OLD_PKG_PREFIX//./\\.}\"/applicationId=\"${NEW_PKG_PREFIX}\"/g" "$file_path" > "$file_path.tmp" && mv "$file_path.tmp" "$file_path"
        fi
    fi
done

echo "Phase 1 (File content update) complete."
echo "-------------------------------------------"

# --- Phase 2: Rename Directories ---
echo "Phase 2: Renaming directories..."

# Find potential source set roots (common ones are /java or /kotlin within src/.../)
# This looks for any directory named 'java' or 'kotlin' that isn't in a build/.gradle/.git path
find . -type d \( -path "./build" -o -path "./.gradle" -o -path "./.git" \) -prune -o \
       -type d \( -name "java" -o -name "kotlin" \) \
       -print | while IFS= read -r source_set_content_root; do

    # Example: source_set_content_root could be ./app/src/main/kotlin or ./core/network/src/main/java

    old_dir_to_move="$source_set_content_root/$OLD_FULL_PATH_SEGMENT"
    new_dir_target="$source_set_content_root/$NEW_FULL_PATH_SEGMENT"

    if [ -d "$old_dir_to_move" ]; then
        echo "Found old directory: $old_dir_to_move"

        # Ensure the parent directory of the new target exists
        # Example: if new_dir_target is .../com/raulp/cardshuffler, this creates .../com/raulp
        mkdir -p "$(dirname "$new_dir_target")"

        echo "Moving $old_dir_to_move to $new_dir_target"
        # Move the old directory to the new location and name
        # Example: mv .../com/skydoves/pokedex .../com/raulp/cardshuffler
        mv "$old_dir_to_move" "$new_dir_target"

        # Attempt to clean up empty parent directories from the old path
        # Example: if com/skydoves/pokedex was moved, and com/skydoves is now empty, remove it.
        # Then if com is empty (less likely), remove it.
        current_path_to_check="$(dirname "$old_dir_to_move")" # Starts with parent of the moved dir (e.g., .../com/skydoves)

        # Loop upwards as long as the path is not the source_set_content_root itself and is a directory
        while [ "$current_path_to_check" != "$source_set_content_root" ] && [ "$current_path_to_check" != "." ] && [ -d "$current_path_to_check" ]; do
            # Check if the directory is empty
            if [ -z "$(ls -A "$current_path_to_check")" ]; then
                echo "Removing empty old parent directory: $current_path_to_check"
                rmdir "$current_path_to_check"
                current_path_to_check="$(dirname "$current_path_to_check")" # Move to the parent of the removed directory
            else
                # If directory is not empty, stop cleaning up this branch
                # echo "Old parent directory $current_path_to_check is not empty. Stopping cleanup for this path."
                break
            fi
        done
    fi
done

echo "Phase 2 (Directory renaming) complete."
echo "-------------------------------------------"
echo "Script finished!"
echo "RECOMMENDATIONS:"
echo "1. In your IDE (Android Studio/IntelliJ), run 'File -> Sync Project with Gradle Files'."
echo "2. Clean and rebuild your project."
echo "3. Thoroughly review the changes using 'git diff' or your IDE's version control tools."
echo "4. Test your application extensively."
