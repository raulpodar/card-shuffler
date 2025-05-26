

package com.raulp.cardshuffler.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.raulp.cardshuffler.compose.core.navigation.AppComposeNavigator
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import com.raulp.cardshuffler.compose.core.navigation.LocalComposeNavigator
import com.raulp.cardshuffler.compose.ui.CardShufflerMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  internal lateinit var composeNavigator: AppComposeNavigator<CardShufflerScreen>

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      CompositionLocalProvider(
        LocalComposeNavigator provides composeNavigator,
      ) {
        CardShufflerMain(composeNavigator = composeNavigator)
      }
    }
  }
}
