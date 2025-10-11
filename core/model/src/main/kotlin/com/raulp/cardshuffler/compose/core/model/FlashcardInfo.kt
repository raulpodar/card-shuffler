

package com.raulp.cardshuffler.compose.core.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class FlashcardInfo(
  @SerialName(value = "id") val id: Int,
  @SerialName(value = "question") val question: String,
  @SerialName(value = "answer") val answer: String,
  @SerialName(value = "subjectId") val subjectId: String,
  val answerHistory: List<Boolean> = emptyList(),
  val totalAnswers: Int = 0,
  val correctAnswers: Int = 0,
) {
  fun getIdString(): String = String.format("#%03d", id)

  fun addAnswer(isCorrect: Boolean): FlashcardInfo {
    val newAnswerHistory = (answerHistory + isCorrect).takeLast(10)
    return this.copy(
      answerHistory = newAnswerHistory,
      totalAnswers = totalAnswers + 1,
      correctAnswers = if (isCorrect) correctAnswers + 1 else correctAnswers,
    )
  }
}
