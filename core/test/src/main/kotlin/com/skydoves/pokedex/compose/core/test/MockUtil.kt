package com.raulp.cardshuffler.compose.core.test

import com.raulp.cardshuffler.compose.core.model.Pokemon
import com.raulp.cardshuffler.compose.core.model.PokemonInfo

object MockUtil {

  fun mockPokemon() = Pokemon(
    page = 0,
    nameField = "bulbasaur",
    url = "https://pokeapi.co/api/v2/pokemon/1/",
  )

  fun mockPokemonList() = listOf(mockPokemon())

  fun mockPokemonInfo() = PokemonInfo(
    id = 1,
    name = "bulbasaur",
    height = 7,
    weight = 69,
    experience = 60,
    types = emptyList(),
    stats = emptyList(),
  )
}
