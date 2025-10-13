

plugins {
  id("raulp.cardshuffler.android.library")
  id("raulp.cardshuffler.spotless")
}

android {
  namespace = "com.raulp.cardshuffler.compose.core.viewmodel"
}

dependencies {
  api(libs.androidx.lifecycle.viewModelCompose)
}
