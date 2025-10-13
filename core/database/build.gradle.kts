

plugins {
  id("raulp.cardshuffler.android.library")
  id("raulp.cardshuffler.android.hilt")
  id("raulp.cardshuffler.spotless")
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.raulp.cardshuffler.compose.core.database"

  defaultConfig {
    // The schemas directory contains a schema file for each version of the Room database.
    // This is required to enable Room auto migrations.
    // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
    ksp {
      arg("room.schemaLocation", "$projectDir/schemas")
    }
  }

  sourceSets.getByName("test") {
    assets.srcDir(files("$projectDir/schemas"))
  }
}

dependencies {
  implementation(projects.core.model)
  testImplementation(projects.core.test)

  // coroutines
  implementation(libs.kotlinx.coroutines.android)
  testImplementation(libs.kotlinx.coroutines.test)

  // database
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.ktx)
  ksp(libs.androidx.room.compiler)
  testImplementation(libs.androidx.arch.core.testing)

  // json parsing
  implementation(libs.kotlinx.serialization.json)

  // unit test
  testImplementation(libs.junit)
  testImplementation(libs.androidx.test.core)
  testImplementation(libs.robolectric)
}