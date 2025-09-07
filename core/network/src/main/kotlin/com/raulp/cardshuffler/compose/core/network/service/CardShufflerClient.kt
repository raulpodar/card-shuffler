

package com.raulp.cardshuffler.compose.core.network.service

import com.raulp.cardshuffler.compose.core.model.PokemonInfo
import com.raulp.cardshuffler.compose.core.network.model.SubjectsResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class CardShufflerClient @Inject constructor(
  private val CardShufflerService: CardShufflerService,
) {

  suspend fun fetchTopicList(): ApiResponse<SubjectsResponse> =
    CardShufflerService.fetchTopics()

  suspend fun fetchPokemonInfo(): List<PokemonInfo> =
  emptyList()
  companion object {
    private const val PAGING_SIZE = 20
  }
}
