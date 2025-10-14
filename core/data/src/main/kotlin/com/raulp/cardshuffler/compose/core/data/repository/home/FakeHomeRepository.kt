

package com.raulp.cardshuffler.compose.core.data.repository.home

import com.raulp.cardshuffler.compose.core.model.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeHomeRepository @Inject constructor() : HomeRepository {
  override fun fetchTopicList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<List<Topic>> {
    onStart()
    val topics = listOf(
      Topic(
        id = "data-structures",
        title = "Data Structures",
        url = "https://placehold.co/600x400/7E57C2/FFFFFF.png?text=Data+Structures",
      ),
      Topic(
        id = "algorithms",
        title = "Algorithms",
        url = "https://placehold.co/600x400/5C6BC0/FFFFFF.png?text=Algorithms",
      ),
    )
    onComplete()
    return flowOf(topics)
  }
}
