

package com.raulp.cardshuffler.compose.core.network.service

import com.raulp.cardshuffler.compose.core.network.model.SubjectsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface CardShufflerService {

  @GET("f41def82cff93da2b936")
  suspend fun fetchTopics(
  ): ApiResponse<SubjectsResponse>
}
