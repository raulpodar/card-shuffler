

plugins {
  id("raulp.cardshuffler.android.library")
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.ksp)
  id("raulp.cardshuffler.spotless")
}

android {
  namespace = "com.raulp.cardshuffler.compose.core.model"
}

dependencies {
  // compose stable marker
  compileOnly(libs.compose.stable.marker)

  // Kotlin Serialization for Json
  implementation(libs.kotlinx.serialization.json)

  // kotlinx
  api(libs.kotlinx.immutable.collection)
}