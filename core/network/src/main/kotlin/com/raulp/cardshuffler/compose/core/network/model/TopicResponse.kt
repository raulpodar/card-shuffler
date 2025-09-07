package com.raulp.cardshuffler.compose.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicResponse(
  @SerialName(value = "id") val id: String,
  @SerialName(value = "image") val image: String?,
  @SerialName(value = "title") val title: String?,
)

@Serializable
data class SubjectsResponse(
  val subjects: List<TopicResponse>
)