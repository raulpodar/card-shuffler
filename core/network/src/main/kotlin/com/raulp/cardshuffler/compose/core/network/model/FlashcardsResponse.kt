package com.raulp.cardshuffler.compose.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class FlashcardsResponse (
  val flashcards: List<FlashcardResponse>
)

@Serializable
data class FlashcardResponse (
  // Correct usage: Pass the expected JSON key as a string
  @SerialName("subject_id") val subjectId: String,
  @SerialName("id") val id: String,
  @SerialName("question") val question: String,
  @SerialName("answer") val answer: String
)