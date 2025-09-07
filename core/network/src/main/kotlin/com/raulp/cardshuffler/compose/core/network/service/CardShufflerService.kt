

package com.raulp.cardshuffler.compose.core.network.service

import com.raulp.cardshuffler.compose.core.network.model.SubjectsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface CardShufflerService {

  @GET("d22a8998e703f94a5d52")
  suspend fun fetchTopics(
  ): ApiResponse<SubjectsResponse>
}
