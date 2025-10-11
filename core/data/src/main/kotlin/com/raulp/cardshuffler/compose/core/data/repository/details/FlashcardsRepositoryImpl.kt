

package com.raulp.cardshuffler.compose.core.data.repository.details

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.raulp.cardshuffler.compose.core.database.FlashcardInfoDao
import com.raulp.cardshuffler.compose.core.database.entitiy.FlashcardInfoEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asDomain
import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asEntity
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import com.raulp.cardshuffler.compose.core.network.CardShufflerAppDispatchers
import com.raulp.cardshuffler.compose.core.network.Dispatcher
import com.raulp.cardshuffler.compose.core.network.service.FlashcardsClient
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@VisibleForTesting
class FlashcardsRepositoryImpl @Inject constructor(
  private val flashcardsClient: FlashcardsClient,
  private val flashcardInfoDao: FlashcardInfoDao,
  @Dispatcher(
    cardShufflerAppDispatchers = CardShufflerAppDispatchers.IO,
  ) private val ioDispatcher: CoroutineDispatcher,
) : FlashcardsRepository {

  @WorkerThread
  override fun fetchFlashcardsBySubject(subjectId: String): Flow<List<FlashcardInfo>> = flow {
    val cachedFlashcards = flashcardInfoDao.getFlashcardsBySubject(subjectId).map {
      it.asDomain()
    }
    if (cachedFlashcards.isNotEmpty()) {
      emit(cachedFlashcards)
    } else {
      val response = flashcardsClient.fetchFlashcards()
      response.suspendOnSuccess {
        val flashcards = data.flashcards
        val flashcardsEntity = flashcards.map {
          FlashcardInfoEntity(
            id = it.id.toInt(),
            subjectId = it.subjectId,
            answer = it.answer,
            question = it.question,
          )
        }

        flashcardInfoDao.insertFlashcardsList(flashcardsEntity)

        emit(flashcardInfoDao.getFlashcardsBySubject(subjectId).map { it.asDomain() })
      }
    }
  }.flowOn(ioDispatcher)

  @WorkerThread
  override suspend fun getAllFlashcards(): Flow<List<FlashcardInfo>> = flow {
    val cachedFlashcards = flashcardInfoDao.getAllFlashcards().map { it.asDomain() }
    if (cachedFlashcards.isNotEmpty()) {
      emit(cachedFlashcards)
    } else {
      val response = flashcardsClient.fetchFlashcards()
      response.suspendOnSuccess {
        val flashcards = data.flashcards
        val flashcardsEntity = flashcards.map {
          FlashcardInfoEntity(
            id = it.id.toInt(),
            subjectId = it.subjectId,
            answer = it.answer,
            question = it.question,
          )
        }

        flashcardInfoDao.insertFlashcardsList(flashcardsEntity)

        emit(flashcardInfoDao.getAllFlashcards().map { it.asDomain() })
      }
    }
  }.flowOn(ioDispatcher)

  @WorkerThread
  override suspend fun updateFlashcard(flashcardInfo: FlashcardInfo) {
    flashcardInfoDao.updateFlashcard(flashcardInfo.asEntity())
  }
}
