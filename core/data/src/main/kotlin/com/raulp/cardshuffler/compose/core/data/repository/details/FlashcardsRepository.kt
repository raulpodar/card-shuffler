

package com.raulp.cardshuffler.compose.core.data.repository.details

import androidx.annotation.WorkerThread
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import kotlinx.coroutines.flow.Flow

interface FlashcardsRepository {

  @WorkerThread
  fun fetchPokemonInfo(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<FlashcardInfo>
}
