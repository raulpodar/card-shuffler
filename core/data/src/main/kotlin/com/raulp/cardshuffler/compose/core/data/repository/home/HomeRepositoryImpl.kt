package com.raulp.cardshuffler.compose.core.data.repository.home

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.raulp.cardshuffler.compose.core.database.TopicsDao
import com.raulp.cardshuffler.compose.core.database.entitiy.TopicEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asDomain
import com.raulp.cardshuffler.compose.core.network.CardShufflerAppDispatchers
import com.raulp.cardshuffler.compose.core.network.Dispatcher
import com.raulp.cardshuffler.compose.core.network.service.TopicsClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@VisibleForTesting
class HomeRepositoryImpl @Inject constructor(
  private val TopicsClient: TopicsClient,
  private val topicsDao: TopicsDao,
  @Dispatcher(cardShufflerAppDispatchers = CardShufflerAppDispatchers.IO) private val ioDispatcher:
  CoroutineDispatcher,
) : HomeRepository {

  @WorkerThread
  override fun fetchTopicList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ) = flow {
    var topics = topicsDao.getAllTopicsList().asDomain()
    Log.d("HomeRepositoryImpl", "Topics: $topics")
    if (topics.isEmpty()) {
      val response = TopicsClient.fetchTopicList()
      Log.d("HomeRepositoryImpl", "Topics: $response")
      response.suspendOnSuccess {
        val topics = data.subjects
        val topicsEntity = topics.map { TopicEntity(it.id, it.image.orEmpty(), it.title.orEmpty()) }
        topicsDao.insertTopicsList(topicsEntity)
        Log.d("HomeRepositoryImpl", "Topics: $topicsEntity")
        emit(topicsDao.getAllTopicsList().asDomain())
      }.onFailure {
        onError(message())
      }
    } else {
      emit(topicsDao.getAllTopicsList().asDomain())
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
