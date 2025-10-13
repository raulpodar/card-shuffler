

package com.raulp.cardshuffler.compose.core.database.entitiy.mapper

import com.raulp.cardshuffler.compose.core.database.entitiy.FlashcardInfoEntity
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo

object FlashcardInfoEntityMapper : EntityMapper<FlashcardInfo, FlashcardInfoEntity> {

  override fun asEntity(domain: FlashcardInfo): FlashcardInfoEntity = FlashcardInfoEntity(
    id = domain.id,
    question = domain.question,
    answer = domain.answer,
    subjectId = domain.subjectId,
  )

  override fun asDomain(entity: FlashcardInfoEntity): FlashcardInfo = FlashcardInfo(
    id = entity.id,
    question = entity.question,
    answer = entity.answer,
    subjectId = entity.subjectId,
  )
}

fun FlashcardInfo.asEntity(): FlashcardInfoEntity = FlashcardInfoEntityMapper.asEntity(this)

fun FlashcardInfoEntity.asDomain(): FlashcardInfo = FlashcardInfoEntityMapper.asDomain(this)
