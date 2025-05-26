

package com.raulp.cardshuffler.compose.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen

@Composable
fun CardShufflerNavHost(navHostController: NavHostController) {
  SharedTransitionLayout {
    NavHost(
      navController = navHostController,
      startDestination = CardShufflerScreen.Home,
    ) {
      cardShufflerNavigation()
    }
  }
}
