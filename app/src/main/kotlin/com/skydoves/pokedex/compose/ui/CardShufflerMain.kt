

package com.raulp.cardshuffler.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.core.navigation.AppComposeNavigator
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import com.raulp.cardshuffler.compose.navigation.CardShufflerNavHost

@Composable
fun CardShufflerMain(composeNavigator: AppComposeNavigator<CardShufflerScreen>) {
  CardShufflerTheme {
    val navHostController = rememberNavController()

    LaunchedEffect(Unit) {
      composeNavigator.handleNavigationCommands(navHostController)
    }

    CardShufflerNavHost(navHostController = navHostController)
  }
}
