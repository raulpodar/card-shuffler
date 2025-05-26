

package com.raulp.cardshuffler.compose.core.network.service

import com.raulp.cardshuffler.compose.core.model.PokemonInfo
import com.raulp.cardshuffler.compose.core.network.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class CardShufflerClient @Inject constructor(
  private val CardShufflerService: CardShufflerService,
) {

  suspend fun fetchPokemonList(page: Int): ApiResponse<PokemonResponse> =
    CardShufflerService.fetchPokemonList(
      limit = PAGING_SIZE,
      offset = page * PAGING_SIZE,
    )

  suspend fun fetchPokemonInfo(name: String): ApiResponse<PokemonInfo> =
    CardShufflerService.fetchPokemonInfo(
      name = name,
    )

  companion object {
    private const val PAGING_SIZE = 20
  }
}
