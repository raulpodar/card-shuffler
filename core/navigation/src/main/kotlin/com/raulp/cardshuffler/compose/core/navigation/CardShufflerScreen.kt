

package com.raulp.cardshuffler.compose.core.navigation

import com.raulp.cardshuffler.compose.core.model.Topic
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface CardShufflerScreen {
  @Serializable
  data object Home : CardShufflerScreen

  @Serializable
  data class Details(val topic: Topic) : CardShufflerScreen {
    companion object {
      val typeMap = mapOf(typeOf<Topic>() to PokemonType)
    }
  }

  @Serializable
  data object CardList : CardShufflerScreen

  @Serializable
  data object SettingsScreen : CardShufflerScreen
}
