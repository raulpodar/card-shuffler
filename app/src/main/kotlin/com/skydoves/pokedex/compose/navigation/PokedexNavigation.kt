

package com.raulp.cardshuffler.compose.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.raulp.cardshuffler.compose.core.navigation.PokedexScreen
import com.raulp.cardshuffler.compose.feature.details.PokedexDetails
import com.raulp.cardshuffler.compose.feature.home.PokedexHome

context(SharedTransitionScope)
fun NavGraphBuilder.pokedexNavigation() {
  composable<PokedexScreen.Home> {
    PokedexHome(this)
  }

  composable<PokedexScreen.Details>(
    typeMap = PokedexScreen.Details.typeMap,
  ) {
    PokedexDetails(this)
  }
}
