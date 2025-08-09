

package com.raulp.cardshuffler.compose.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import com.raulp.cardshuffler.compose.feature.details.CardShufflerDetails
import com.raulp.cardshuffler.compose.feature.home.CardShufflerHome

context(SharedTransitionScope)
fun NavGraphBuilder.cardShufflerNavigation() {
  composable<CardShufflerScreen.Home> {
    CardShufflerHome(this)
  }

  composable<CardShufflerScreen.Details>(
    typeMap = CardShufflerScreen.Details.typeMap,
  ) {
    CardShufflerDetails(this)
  }

  composable<CardShufflerScreen.CardList> {
    CardListScreen()
  }

  composable<CardShufflerScreen.SettingsScreen> {
    SettingsScreen()
  }
}

@Composable
fun CardListScreen() {
  Text(text = "Card List Screen")
}

@Composable
fun SettingsScreen() {
  Text(text = "Settings Screen")
}
