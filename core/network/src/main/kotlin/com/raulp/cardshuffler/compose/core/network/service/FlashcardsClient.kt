

package com.raulp.cardshuffler.compose.core.network.service

import com.raulp.cardshuffler.compose.core.network.model.FlashcardsResponse
import com.skydoves.sandwich.ApiResponse
import jakarta.inject.Inject

class FlashcardsClient @Inject constructor(private val flashcardsService: FlashcardsService) {
  suspend fun fetchFlashcards(): ApiResponse<FlashcardsResponse> =
    flashcardsService.fetchFlashcards()
}
