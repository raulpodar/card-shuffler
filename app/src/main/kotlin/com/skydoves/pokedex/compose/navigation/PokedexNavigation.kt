package com.raulp.cardshuffler.compose.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import com.raulp.cardshuffler.compose.feature.details.CardShufflerDetails
import com.raulp.cardshuffler.compose.feature.home.CardShufflerHome

context(SharedTransitionScope)
fun NavGraphBuilder.CardShufflerNavigation() {
  composable<CardShufflerScreen.Home> {
    CardShufflerHome(this)
  }

  composable<CardShufflerScreen.Details>(
    typeMap = CardShufflerScreen.Details.typeMap,
  ) {
    CardShufflerDetails(this)
  }
}
