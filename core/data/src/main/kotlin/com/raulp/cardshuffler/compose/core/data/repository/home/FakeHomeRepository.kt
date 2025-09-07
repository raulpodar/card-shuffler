

package com.raulp.cardshuffler.compose.core.data.repository.home

import com.raulp.cardshuffler.compose.core.model.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeHomeRepository : HomeRepository {
  override fun fetchTopicList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<List<Topic>> = flowOf()
}
