

package com.raulp.cardshuffler.compose.core.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.raulp.cardshuffler.compose.core.database.converter.AnswerHistoryConverter

@Entity
data class FlashcardInfoEntity(
  @PrimaryKey val id: Int,
  val subjectId: String,
  val question: String,
  val answer: String,
  @TypeConverters(AnswerHistoryConverter::class)
  val answerHistory: List<Boolean> = emptyList(),
  val totalAnswers: Int = 0,
  val correctAnswers: Int = 0,
)
