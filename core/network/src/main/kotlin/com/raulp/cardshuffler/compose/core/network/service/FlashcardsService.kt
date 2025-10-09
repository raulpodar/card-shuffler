

package com.raulp.cardshuffler.compose.core.network.service

import com.raulp.cardshuffler.compose.core.network.model.FlashcardsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface FlashcardsService {

  @GET("217a1ed3d79c3008afe7")
  suspend fun fetchFlashcards(): ApiResponse<FlashcardsResponse>
}
