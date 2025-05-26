

package com.raulp.cardshuffler.compose.core.navigation

import com.raulp.cardshuffler.compose.core.model.Pokemon
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface CardShufflerScreen {
  @Serializable
  data object Home : CardShufflerScreen {
    val route = "home"
  }

  @Serializable
  data class Details(val pokemon: Pokemon) : CardShufflerScreen {
    companion object {
      val typeMap = mapOf(typeOf<Pokemon>() to PokemonType)
    }
  }

  @Serializable
  data object CardList : CardShufflerScreen {
    val route = "card_list"
  }

  @Serializable
  data object SettingsScreen : CardShufflerScreen {
    val route = "settings"
  }
}
