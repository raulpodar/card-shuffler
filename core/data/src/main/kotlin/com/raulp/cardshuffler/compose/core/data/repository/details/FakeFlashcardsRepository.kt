

package com.raulp.cardshuffler.compose.core.data.repository.details

import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeFlashcardsRepository @Inject constructor() : FlashcardsRepository {

  private val flashcards = listOf(
    FlashcardInfo(
      id = 1,
      subjectId = "data-structures",
      question = "What is an Array?",
      answer = "A data structure that stores a collection of elements," +
        "each identified by at least one array index or key.",
      answerHistory = emptyList(),
      correctAnswers = 0,
      totalAnswers = 0,
    ),
  )

  override fun fetchFlashcardsBySubject(subjectId: String): Flow<List<FlashcardInfo>> = flowOf(
    flashcards.filter {
      it.subjectId == subjectId
    },
  )

  override suspend fun getAllFlashcards(): Flow<List<FlashcardInfo>> = flowOf(flashcards)

  override suspend fun updateFlashcard(flashcardInfo: FlashcardInfo) {
    // No-op for fake
  }
}
