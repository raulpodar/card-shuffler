package com.raulp.cardshuffler.compose.core.network.model.mapper

import com.raulp.cardshuffler.compose.core.model.Topic
import com.raulp.cardshuffler.compose.core.network.model.SubjectsResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mappers.ApiSuccessModelMapper

object TopicResponseMapper : ApiSuccessModelMapper<SubjectsResponse, List<Topic>> {

  override fun map(apiSuccessResponse: ApiResponse.Success<SubjectsResponse>): List<Topic> {
    return apiSuccessResponse.data.subjects.map { topicResponse ->
      Topic(
        id = topicResponse.id,
        url = topicResponse.image.orEmpty(),
        title = topicResponse.title.orEmpty()
      )
    }

  }
}
