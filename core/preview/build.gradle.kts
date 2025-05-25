plugins {
  id("raulp.cardshuffler.android.library")
  id("raulp.cardshuffler.android.library.compose")
  id("raulp.cardshuffler.android.hilt")
  id("raulp.cardshuffler.spotless")
}

android {
  namespace = "com.raulp.cardshuffler.compose.feature.preview"
}

dependencies {
  // core
  implementation(projects.core.designsystem)
  implementation(projects.core.navigation)
  implementation(projects.core.model)
}