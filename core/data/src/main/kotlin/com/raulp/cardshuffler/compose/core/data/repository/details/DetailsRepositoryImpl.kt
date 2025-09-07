

package com.raulp.cardshuffler.compose.core.data.repository.details

import androidx.annotation.VisibleForTesting
import com.raulp.cardshuffler.compose.core.database.PokemonInfoDao
import com.raulp.cardshuffler.compose.core.model.PokemonInfo
import com.raulp.cardshuffler.compose.core.network.CardShufflerAppDispatchers
import com.raulp.cardshuffler.compose.core.network.Dispatcher
import com.raulp.cardshuffler.compose.core.network.service.CardShufflerClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@VisibleForTesting
class DetailsRepositoryImpl @Inject constructor(
  private val CardShufflerClient: CardShufflerClient,
  private val pokemonInfoDao: PokemonInfoDao,
  @Dispatcher(cardShufflerAppDispatchers = CardShufflerAppDispatchers.IO) private val ioDispatcher:
  CoroutineDispatcher,
) : DetailsRepository {

  //  @WorkerThread
//  override fun fetchPokemonInfo(name: String, onComplete: () -> Unit, onError: (String?) -> Unit) =
//    flow {
//      val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
//      if (pokemonInfo == null) {
//        /**
//         * fetches a [PokemonInfo] from the network and getting [ApiResponse] asynchronously.
//         * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
//         */
//        val response = CardShufflerClient.fetchPokemonInfo(name = name)
//        response.suspendOnSuccess {
//          pokemonInfoDao.insertPokemonInfo(data.asEntity())
//          emit(data)
//        }
//          // handles the case when the API request gets an error response.
//          // e.g., internal server error.
//          .onError {
//            /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
//            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
//          }
//          // handles the case when the API request gets an exception response.
//          // e.g., network connection error.
//          .onException { onError(message) }
//      } else {
//        emit(pokemonInfo.asDomain())
//      }
//    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
  override fun fetchPokemonInfo(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ): Flow<PokemonInfo> {
    return emptyFlow()
  }
}
