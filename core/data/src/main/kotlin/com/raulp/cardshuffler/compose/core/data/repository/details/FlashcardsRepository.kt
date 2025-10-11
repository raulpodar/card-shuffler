

package com.raulp.cardshuffler.compose.core.data.repository.details

import androidx.annotation.WorkerThread
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import kotlinx.coroutines.flow.Flow

interface FlashcardsRepository {
  @WorkerThread
  fun fetchFlashcardsBySubject(subjectId: String): Flow<List<FlashcardInfo>>

  @WorkerThread
  suspend fun getAllFlashcards(): Flow<List<FlashcardInfo>>

  @WorkerThread
  suspend fun updateFlashcard(flashcardInfo: FlashcardInfo)
}
