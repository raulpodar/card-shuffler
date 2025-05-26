

package com.raulp.cardshuffler.compose.core.database.entitiy.mapper

import com.raulp.cardshuffler.compose.core.database.entitiy.PokemonEntity
import com.raulp.cardshuffler.compose.core.model.Pokemon

object PokemonEntityMapper : EntityMapper<List<Pokemon>, List<PokemonEntity>> {

  override fun asEntity(domain: List<Pokemon>): List<PokemonEntity> = domain.map { pokemon ->
    PokemonEntity(
      page = pokemon.page,
      name = pokemon.name,
      url = pokemon.url,
    )
  }

  override fun asDomain(entity: List<PokemonEntity>): List<Pokemon> = entity.map { pokemonEntity ->
    Pokemon(
      page = pokemonEntity.page,
      nameField = pokemonEntity.name,
      url = pokemonEntity.url,
    )
  }
}

fun List<Pokemon>.asEntity(): List<PokemonEntity> = PokemonEntityMapper.asEntity(this)

fun List<PokemonEntity>?.asDomain(): List<Pokemon> = PokemonEntityMapper.asDomain(this.orEmpty())
