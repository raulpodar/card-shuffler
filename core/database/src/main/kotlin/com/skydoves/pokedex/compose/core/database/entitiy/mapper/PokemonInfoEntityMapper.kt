

package com.raulp.cardshuffler.compose.core.database.entitiy.mapper

import com.raulp.cardshuffler.compose.core.database.entitiy.PokemonInfoEntity
import com.raulp.cardshuffler.compose.core.model.PokemonInfo

object PokemonInfoEntityMapper : EntityMapper<PokemonInfo, PokemonInfoEntity> {

  override fun asEntity(domain: PokemonInfo): PokemonInfoEntity = PokemonInfoEntity(
    id = domain.id,
    name = domain.name,
    height = domain.height,
    weight = domain.weight,
    experience = domain.experience,
    types = domain.types,
    exp = domain.exp,
    stats = domain.stats,
  )

  override fun asDomain(entity: PokemonInfoEntity): PokemonInfo = PokemonInfo(
    id = entity.id,
    name = entity.name,
    height = entity.height,
    weight = entity.weight,
    experience = entity.experience,
    types = entity.types,
    exp = entity.exp,
    stats = entity.stats,
  )
}

fun PokemonInfo.asEntity(): PokemonInfoEntity = PokemonInfoEntityMapper.asEntity(this)

fun PokemonInfoEntity.asDomain(): PokemonInfo = PokemonInfoEntityMapper.asDomain(this)
