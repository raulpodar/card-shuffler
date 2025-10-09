

package com.raulp.cardshuffler.compose.core.test

import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import com.raulp.cardshuffler.compose.core.model.Topic

object MockUtil {

  fun mockPokemon() = Topic(
    page = 0,
    topicTitle = "bulbasaur",
    url = "https://pokeapi.co/api/v2/pokemon/1/",
  )

  fun mockPokemonList() = listOf(mockPokemon())

  fun mockPokemonInfo() = FlashcardInfo(
    id = 1,
    name = "bulbasaur",
    height = 7,
    weight = 69,
    experience = 60,
    types = emptyList(),
    stats = emptyList(),
  )
}
