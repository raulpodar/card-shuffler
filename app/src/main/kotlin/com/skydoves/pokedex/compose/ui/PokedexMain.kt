

package com.raulp.cardshuffler.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.raulp.cardshuffler.compose.core.designsystem.theme.PokedexTheme
import com.raulp.cardshuffler.compose.core.navigation.AppComposeNavigator
import com.raulp.cardshuffler.compose.core.navigation.PokedexScreen
import com.raulp.cardshuffler.compose.navigation.PokedexNavHost

@Composable
fun PokedexMain(composeNavigator: AppComposeNavigator<PokedexScreen>) {
  PokedexTheme {
    val navHostController = rememberNavController()

    LaunchedEffect(Unit) {
      composeNavigator.handleNavigationCommands(navHostController)
    }

    PokedexNavHost(navHostController = navHostController)
  }
}
