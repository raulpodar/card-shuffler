

package com.raulp.cardshuffler.compose.core.navigation

import com.raulp.cardshuffler.compose.core.model.Pokemon
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface CardShufflerScreen {
  @Serializable
  data object Home : CardShufflerScreen

  @Serializable
  data class Details(val pokemon: Pokemon) : CardShufflerScreen {
    companion object {
      val typeMap = mapOf(typeOf<Pokemon>() to PokemonType)
    }
  }
}
