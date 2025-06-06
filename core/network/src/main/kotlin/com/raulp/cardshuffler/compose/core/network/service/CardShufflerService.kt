

package com.raulp.cardshuffler.compose.core.network.service

import com.raulp.cardshuffler.compose.core.model.PokemonInfo
import com.raulp.cardshuffler.compose.core.network.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CardShufflerService {

  @GET("pokemon")
  suspend fun fetchPokemonList(
    @Query("limit") limit: Int = 20,
    @Query("offset") offset: Int = 0,
  ): ApiResponse<PokemonResponse>

  @GET("pokemon/{name}")
  suspend fun fetchPokemonInfo(@Path("name") name: String): ApiResponse<PokemonInfo>
}
