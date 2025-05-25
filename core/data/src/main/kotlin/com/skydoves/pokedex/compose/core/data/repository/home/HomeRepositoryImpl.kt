

package com.raulp.cardshuffler.compose.core.data.repository.home

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.raulp.cardshuffler.compose.core.database.PokemonDao
import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asDomain
import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asEntity
import com.raulp.cardshuffler.compose.core.model.Pokemon
import com.raulp.cardshuffler.compose.core.network.Dispatcher
import com.raulp.cardshuffler.compose.core.network.CardShufflerAppDispatchers
import com.raulp.cardshuffler.compose.core.network.service.PokedexClient
import com.skydoves.sandwich.ApiResponse
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
  private val pokedexClient: PokedexClient,
  private val pokemonDao: PokemonDao,
  @Dispatcher(CardShufflerAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : HomeRepository {

  @WorkerThread
  override fun fetchPokemonList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ) = flow {
    var pokemons = pokemonDao.getPokemonList(page).asDomain()
    if (pokemons.isEmpty()) {
      /**
       * fetches a list of [Pokemon] from the network and getting [ApiResponse] asynchronously.
       * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
       */
      val response = pokedexClient.fetchPokemonList(page = page)
      response.suspendOnSuccess {
        pokemons = data.results
        pokemons.forEach { pokemon -> pokemon.page = page }
        pokemonDao.insertPokemonList(pokemons.asEntity())
        emit(pokemonDao.getAllPokemonList(page).asDomain())
      }.onFailure { // handles the all error cases from the API request fails.
        onError(message())
      }
    } else {
      emit(pokemonDao.getAllPokemonList(page).asDomain())
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
