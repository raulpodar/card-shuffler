

plugins {
  id("raulp.cardshuffler.android.library")
  id("raulp.cardshuffler.spotless")
}

android {
  namespace = "com.raulp.cardshuffler.compose.core.test"
}

dependencies {
  implementation(projects.core.model)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.test)
  implementation(libs.junit)
}