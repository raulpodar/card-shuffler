

package com.raulp.cardshuffler.compose

import Flashcard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.raulp.cardshuffler.compose.core.navigation.AppComposeNavigator
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  internal lateinit var composeNavigator: AppComposeNavigator<CardShufflerScreen>

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

//    setContent {
//      CompositionLocalProvider(
//        LocalComposeNavigator provides composeNavigator,
//      ) {
//        CardShufflerMain(composeNavigator = composeNavigator)
//      }
//    }

    setContent {
      MaterialTheme { // Or your app's theme
        Surface(modifier = Modifier.fillMaxSize()) {
          Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            Flashcard(
              question = "What is the capital of France?",
              answer = "Paris",
              onKnewThis = { /* ... */ },
              onNeedToPractice = { /* ... */ }
            )
          }
        }
      }
    }
  }
}
