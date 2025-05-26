

package com.raulp.cardshuffler.compose.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import com.raulp.cardshuffler.compose.feature.details.CardShufflerDetails
import com.raulp.cardshuffler.compose.feature.home.CardShufflerHome
import com.raulp.cardshuffler.compose.feature.cardlist.CardListScreen // Updated import
import com.raulp.cardshuffler.compose.feature.settings.SettingsScreen

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

  composable<CardShufflerScreen.CardList> { // Updated route and call
    CardListScreen()
  }

  composable<CardShufflerScreen.SettingsScreen> {
    SettingsScreen()
  }
}
