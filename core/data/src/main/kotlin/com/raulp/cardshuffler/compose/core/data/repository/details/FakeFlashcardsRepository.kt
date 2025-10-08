

package com.raulp.cardshuffler.compose.core.data.repository.details

import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeFlashcardsRepository : FlashcardsRepository {

  override fun fetchPokemonInfo(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<FlashcardInfo> = flowOf()
}
