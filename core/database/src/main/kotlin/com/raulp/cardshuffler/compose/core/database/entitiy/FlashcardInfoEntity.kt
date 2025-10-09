

package com.raulp.cardshuffler.compose.core.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlashcardInfoEntity(
  @PrimaryKey val id: Int,
  val subjectId: String,
  val question: String,
  val answer: String,
)
