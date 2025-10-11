

package com.raulp.cardshuffler.compose.core.data.repository.details

import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import kotlinx.coroutines.flow.Flow

class FakeFlashcardsRepository : FlashcardsRepository {

  override fun fetchFlashcardsBySubject(subjectId: String): Flow<List<FlashcardInfo>> {
    TODO("Not yet implemented")
  }

  override suspend fun getAllFlashcards(): Flow<List<FlashcardInfo>> {
    TODO("Not yet implemented")
  }

  override suspend fun updateFlashcard(flashcardInfo: FlashcardInfo) {
    TODO("Not yet implemented")
  }
}
