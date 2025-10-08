

package com.raulp.cardshuffler.compose.core.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Immutable
@Serializable
data class FlashcardInfo(
  @SerialName(value = "id") val id: Int,
  @SerialName(value = "question") val question: String,
  @SerialName(value = "answer") val answer: String,
  @SerialName(value = "subjectId") val subjectId: String
) {
  fun getIdString(): String = String.format("#%03d", id)

}
