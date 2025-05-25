

package com.raulp.cardshuffler.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.raulp.cardshuffler.compose.core.navigation.AppComposeNavigator
import com.raulp.cardshuffler.compose.core.navigation.LocalComposeNavigator
import com.raulp.cardshuffler.compose.core.navigation.PokedexScreen
import com.raulp.cardshuffler.compose.ui.PokedexMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  internal lateinit var composeNavigator: AppComposeNavigator<PokedexScreen>

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      CompositionLocalProvider(
        LocalComposeNavigator provides composeNavigator,
      ) {
        PokedexMain(composeNavigator = composeNavigator)
      }
    }
  }
}
