package com.raulp.cardshuffler.compose.core.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
data class FlashcardInfoEntity(
  @PrimaryKey val id: Int,
  val subjectId: String,
  val question: String,
  val answer: String
)
