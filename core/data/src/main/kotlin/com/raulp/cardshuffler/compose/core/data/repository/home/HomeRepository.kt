

package com.raulp.cardshuffler.compose.core.data.repository.home

import androidx.annotation.WorkerThread
import com.raulp.cardshuffler.compose.core.model.Topic
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

  @WorkerThread
  fun fetchTopicList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<List<Topic>>
}
