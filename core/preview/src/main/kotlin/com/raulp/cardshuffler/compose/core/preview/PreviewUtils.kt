

package com.raulp.cardshuffler.compose.core.preview

import com.raulp.cardshuffler.compose.core.model.PokemonInfo
import com.raulp.cardshuffler.compose.core.model.Topic

object PreviewUtils {

  fun mockPokemon() = Topic(
    title = "bulbasaur",
    id = "android",
    url = "https://pokeapi.co/api/v2/pokemon/1/",
  )

  fun mockPokemonList() = List(10) {
    Topic(id = "android" , title = "bulbasaur$it", url = "")
  }

  fun mockPokemonInfo() = PokemonInfo(
    id = 1,
    name = "bulbasaur",
    height = 7,
    weight = 69,
    experience = 60,
    types = listOf(
      PokemonInfo.TypeResponse(slot = 0, type = PokemonInfo.Type("grass")),
      PokemonInfo.TypeResponse(slot = 0, type = PokemonInfo.Type("poison")),
    ),
    stats = listOf(
      PokemonInfo.StatsResponse(baseStat = 20, effort = 0, stat = PokemonInfo.Stat("hp")),
      PokemonInfo.StatsResponse(baseStat = 40, effort = 0, stat = PokemonInfo.Stat("attack")),
      PokemonInfo.StatsResponse(baseStat = 60, effort = 0, stat = PokemonInfo.Stat("defense")),
      PokemonInfo.StatsResponse(baseStat = 80, effort = 0, stat = PokemonInfo.Stat("attack")),
    ),
  )
}
