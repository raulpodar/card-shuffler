

package com.raulp.cardshuffler.compose.core.network.service

import com.raulp.cardshuffler.compose.core.network.model.SubjectsResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class TopicsClient @Inject constructor(private val cardShufflerService: CardShufflerService) {

  suspend fun fetchTopicList(): ApiResponse<SubjectsResponse> = cardShufflerService.fetchTopics()
}
