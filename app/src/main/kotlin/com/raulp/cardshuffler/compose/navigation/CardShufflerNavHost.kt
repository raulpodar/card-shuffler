

package com.raulp.cardshuffler.compose.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import androidx.compose.ui.Modifier

@Composable
fun CardShufflerNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
  SharedTransitionLayout {
    NavHost(
      navController = navHostController,
      startDestination = CardShufflerScreen.Home,
      modifier = modifier
    ) {
      cardShufflerNavigation()
    }
  }
}
