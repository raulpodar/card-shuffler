package com.raulp.cardshuffler.compose.core.data.repository.details

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.raulp.cardshuffler.compose.core.database.FlashcardInfoDao
import com.raulp.cardshuffler.compose.core.database.entitiy.FlashcardInfoEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asDomain
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import com.raulp.cardshuffler.compose.core.network.CardShufflerAppDispatchers
import com.raulp.cardshuffler.compose.core.network.Dispatcher
import com.raulp.cardshuffler.compose.core.network.service.FlashcardsClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@VisibleForTesting
class FlashcardsRepositoryImpl @Inject constructor(
  private val flashcardsClient: FlashcardsClient,
  private val flashcardInfoDao: FlashcardInfoDao,
  @Dispatcher(cardShufflerAppDispatchers = CardShufflerAppDispatchers.IO) private val ioDispatcher:
  CoroutineDispatcher,
) : FlashcardsRepository {

  @WorkerThread
  override fun fetchPokemonInfo(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ): Flow<FlashcardInfo> = flow {
    val cachedFlashcard = flashcardInfoDao.getAllFlashcards()?.asDomain()
    if (cachedFlashcard != null) {
      emit(cachedFlashcard)
    }
    val response = flashcardsClient.fetchFlashcards()
    response.suspendOnSuccess {
      val flashcards = data.flashcards
      val flashcardsEntity = flashcards.map {
        FlashcardInfoEntity(
          id = it.id.toInt(),
          subjectId = it.subjectId,
          answer = it.answer,
          question = it.question
        )
      }


      flashcardInfoDao.insertFlashcardsList(flashcardsEntity)

      emit(flashcardInfoDao.getAllFlashcards()!!.asDomain())
    }
      .onFailure {
        onError(message())
      }
  }.onCompletion { onComplete() }
    .flowOn(ioDispatcher) // Ensure all operations run on the IO thread.
}