

package com.raulp.cardshuffler.compose.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Topic(
  @SerialName(value = "id") val id: String,
  @SerialName(value = "url") val url: String,
  @SerialName(value = "title") val title: String,
) : Parcelable {

  val topicTitle: String
    get() = title.replaceFirstChar { it.uppercase() }

  val imageUrl: String
    inline get() {
      return url
    }
}
