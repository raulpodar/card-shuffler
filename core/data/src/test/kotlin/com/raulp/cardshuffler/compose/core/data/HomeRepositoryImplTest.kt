//
//
//package com.raulp.cardshuffler.compose.core.data
//
//import app.cash.turbine.test
//import com.raulp.cardshuffler.compose.core.data.repository.home.HomeRepositoryImpl
//import com.raulp.cardshuffler.compose.core.database.TopicsDao
//import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asEntity
//import com.raulp.cardshuffler.compose.core.network.model.TopicResponse
//import com.raulp.cardshuffler.compose.core.network.service.CardShufflerClient
//import com.raulp.cardshuffler.compose.core.network.service.CardShufflerService
//import com.raulp.cardshuffler.compose.core.test.MainCoroutinesRule
//import com.raulp.cardshuffler.compose.core.test.MockUtil.mockPokemonList
//import com.skydoves.sandwich.ApiResponse
//import com.skydoves.sandwich.retrofit.responseOf
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.kotlin.atLeastOnce
//import org.mockito.kotlin.mock
//import org.mockito.kotlin.verify
//import org.mockito.kotlin.verifyNoMoreInteractions
//import org.mockito.kotlin.whenever
//import retrofit2.Response
//import kotlin.time.DurationUnit
//import kotlin.time.toDuration
//
//class HomeRepositoryImplTest {
//
//  private lateinit var repository: HomeRepositoryImpl
//  private lateinit var client: CardShufflerClient
//  private val service: CardShufflerService = mock()
//  private val topicsDao: TopicsDao = mock()
//
//  @get:Rule
//  val coroutinesRule = MainCoroutinesRule()
//
//  @Before
//  fun setup() {
//    client = CardShufflerClient(service)
//    repository = HomeRepositoryImpl(client, topicsDao, coroutinesRule.testDispatcher)
//  }
//
//  @Test
//  fun fetchPokemonListFromNetworkTest() = runTest {
//    val mockData =
//      TopicResponse(id = 984, image = null, title = null)
//    whenever(topicsDao.getTopicsList()).thenReturn(emptyList())
//    whenever(topicsDao.getAllTopicsList()).thenReturn(mockData.asEntity())
//    whenever(service.fetchTopics()).thenReturn(
//      ApiResponse.responseOf {
//        Response.success(
//          mockData,
//        )
//      },
//    )
//
//    repository.fetchPokemonList(
//      page = 0,
//      onStart = {},
//      onComplete = {},
//      onError = {},
//    ).test(2.toDuration(DurationUnit.SECONDS)) {
//      val actualItem = awaitItem()[0]
//      assertEquals(0, actualItem.page)
//      // First letter of name is always upper case
//      assertEquals("Bulbasaur", actualItem.name)
//      assertEquals("https://pokeapi.co/api/v2/pokemon/1/", actualItem.url)
//      awaitComplete()
//    }
//
//    verify(topicsDao, atLeastOnce()).getTopicsList(page_ = 0)
//    verify(service, atLeastOnce()).fetchTopics()
//    verify(topicsDao, atLeastOnce()).insertPokemonList(mockData.results.asEntity())
//    verifyNoMoreInteractions(service)
//  }
//
//  @Test
//  fun fetchPokemonListFromDatabaseTest() = runTest {
//    val mockData =
//      TopicResponse(id = 984, image = null, title = null, results = mockPokemonList())
//    whenever(topicsDao.getTopicsList(page_ = 0)).thenReturn(mockData.results.asEntity())
//    whenever(topicsDao.getAllTopicsList(page_ = 0)).thenReturn(mockData.results.asEntity())
//
//    repository.fetchPokemonList(
//      page = 0,
//      onStart = {},
//      onComplete = {},
//      onError = {},
//    ).test(2.toDuration(DurationUnit.SECONDS)) {
//      val actualItem = awaitItem()[0]
//      assertEquals(0, actualItem.page)
//      // First letter of name is always upper case
//      assertEquals("Bulbasaur", actualItem.name)
//      assertEquals("https://pokeapi.co/api/v2/pokemon/1/", actualItem.url)
//      awaitComplete()
//    }
//
//    verify(topicsDao, atLeastOnce()).getTopicsList(page_ = 0)
//    verify(topicsDao, atLeastOnce()).getAllTopicsList(page_ = 0)
//  }
//}
