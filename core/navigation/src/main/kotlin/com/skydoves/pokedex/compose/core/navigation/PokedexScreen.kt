

package com.raulp.cardshuffler.compose.core.navigation

import com.raulp.cardshuffler.compose.core.model.Pokemon
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface PokedexScreen {
  @Serializable
  data object Home : PokedexScreen

  @Serializable
  data class Details(val pokemon: Pokemon) : PokedexScreen {
    companion object {
      val typeMap = mapOf(typeOf<Pokemon>() to PokemonType)
    }
  }
}
