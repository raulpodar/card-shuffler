package com.raulp.cardshuffler.compose.core.database.entitiy.mapper

import com.raulp.cardshuffler.compose.core.database.entitiy.TopicEntity
import com.raulp.cardshuffler.compose.core.model.Topic

object TopicEntityMapper : EntityMapper<List<Topic>, List<TopicEntity>> {

  override fun asEntity(domain: List<Topic>): List<TopicEntity> = domain.map { topic ->
    TopicEntity(
      id = topic.id,
      url = topic.url,
      title = topic.title
    )
  }

  override fun asDomain(entity: List<TopicEntity>): List<Topic> = entity.map { topicEntity ->
    Topic(
      id = topicEntity.id,
      url = topicEntity.url,
      title = topicEntity.title
    )
  }
}

fun List<Topic>.asEntity(): List<TopicEntity> = TopicEntityMapper.asEntity(this)
fun List<TopicEntity>?.asDomain(): List<Topic> = TopicEntityMapper.asDomain(this.orEmpty())
