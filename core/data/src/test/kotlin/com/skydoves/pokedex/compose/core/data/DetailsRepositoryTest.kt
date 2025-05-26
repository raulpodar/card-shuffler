package com.raulp.cardshuffler.compose.core.data

import app.cash.turbine.test
import com.raulp.cardshuffler.compose.core.data.repository.details.DetailsRepositoryImpl
import com.raulp.cardshuffler.compose.core.database.PokemonInfoDao
import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asEntity
import com.raulp.cardshuffler.compose.core.network.service.CardShufflerClient
import com.raulp.cardshuffler.compose.core.network.service.CardShufflerService
import com.raulp.cardshuffler.compose.core.test.MainCoroutinesRule
import com.raulp.cardshuffler.compose.core.test.MockUtil.mockPokemonInfo
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class DetailsRepositoryTest {

  private lateinit var repository: DetailsRepositoryImpl
  private lateinit var client: CardShufflerClient
  private val service: CardShufflerService = mock()
  private val pokemonInfoDao: PokemonInfoDao = mock()

  @get:Rule
  val coroutinesRule = MainCoroutinesRule()

  @Before
  fun setup() {
    client = CardShufflerClient(service)
    repository = DetailsRepositoryImpl(client, pokemonInfoDao, coroutinesRule.testDispatcher)
  }

  @Test
  fun fetchPokemonInfoFromNetworkTest() = runTest {
    val mockData = mockPokemonInfo()
    whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(null)
    whenever(service.fetchPokemonInfo(name = "bulbasaur")).thenReturn(
      ApiResponse.responseOf {
        Response.success(
          mockData,
        )
      },
    )

    repository.fetchPokemonInfo(name = "bulbasaur", onComplete = {}, onError = {}).test {
      val actualItem = awaitItem()
      assertEquals(mockData.id, actualItem.id)
      assertEquals(mockData.name, actualItem.name)
      assertEquals(mockData, actualItem)
      awaitComplete()
    }

    verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
    verify(service, atLeastOnce()).fetchPokemonInfo(name = "bulbasaur")
    verify(pokemonInfoDao, atLeastOnce()).insertPokemonInfo(mockData.asEntity())
    verifyNoMoreInteractions(service)
  }

  @Test
  fun fetchPokemonInfoFromDatabaseTest() = runTest {
    val mockData = mockPokemonInfo()
    whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(mockData.asEntity())
    whenever(service.fetchPokemonInfo(name = "bulbasaur")).thenReturn(
      ApiResponse.responseOf {
        Response.success(
          mockData,
        )
      },
    )

    repository.fetchPokemonInfo(
      name = "bulbasaur",
      onComplete = {},
      onError = {},
    ).test(5.toDuration(DurationUnit.SECONDS)) {
      val expectItem = awaitItem()
      assertEquals(expectItem.id, mockData.id)
      assertEquals(expectItem.name, mockData.name)
      assertEquals(expectItem, mockData)
      awaitComplete()
    }

    verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
    verifyNoMoreInteractions(service)
  }
}
