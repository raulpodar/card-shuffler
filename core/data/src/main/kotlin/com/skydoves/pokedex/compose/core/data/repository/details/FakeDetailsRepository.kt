package com.raulp.cardshuffler.compose.core.data.repository.details

import com.raulp.cardshuffler.compose.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDetailsRepository : DetailsRepository {

  override fun fetchPokemonInfo(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<PokemonInfo> = flowOf()
}
