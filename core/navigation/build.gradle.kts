plugins {
  id("raulp.cardshuffler.android.library")
  id("raulp.cardshuffler.android.library.compose")
  alias(libs.plugins.kotlinx.serialization)
  id("raulp.cardshuffler.android.hilt")
  id("raulp.cardshuffler.spotless")
}

android {
  namespace = "com.raulp.cardshuffler.compose.core.navigation"
}

dependencies {
  implementation(projects.core.model)

  implementation(libs.androidx.core)
  implementation(libs.kotlinx.coroutines.android)

  api(libs.androidx.navigation.compose)

  // json parsing
  implementation(libs.kotlinx.serialization.json)
}