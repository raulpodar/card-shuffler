package com.raulp.cardshuffler.compose.core.database.entitiy.mapper

import com.raulp.cardshuffler.compose.core.database.entitiy.AndroidTopicEntity
import com.raulp.cardshuffler.compose.core.model.AndroidTopic

object AndroidTopicEntityMapper : EntityMapper<List<AndroidTopic>, List<AndroidTopicEntity>> {

  override fun asEntity(domain: List<AndroidTopic>): List<AndroidTopicEntity> = emptyList()

  override fun asDomain(entity: List<AndroidTopicEntity>): List<AndroidTopic> = emptyList()
  }

fun List<AndroidTopic>.asEntity(): List<AndroidTopicEntity> = AndroidTopicEntityMapper.asEntity(this)

fun List<AndroidTopicEntity>?.asDomain(): List<AndroidTopic> = AndroidTopicEntityMapper.asDomain(this.orEmpty())
