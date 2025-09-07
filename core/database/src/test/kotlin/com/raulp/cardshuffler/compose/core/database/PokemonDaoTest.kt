

package com.raulp.cardshuffler.compose.core.database

import com.raulp.cardshuffler.compose.core.database.entitiy.mapper.asEntity
import com.raulp.cardshuffler.compose.core.test.MockUtil.mockPokemon
import com.raulp.cardshuffler.compose.core.test.MockUtil.mockPokemonList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class PokemonDaoTest : LocalDatabase() {

  private lateinit var topicsDao: TopicsDao

  @Before
  fun init() {
    topicsDao = db.pokemonDao()
  }

  @Test
  fun insertAndLoadPokemonListTest() = runBlocking {
    val mockDataList = mockPokemonList().asEntity()
    topicsDao.insertTopicsList(mockDataList)

    val loadFromDB = topicsDao.getTopicsList()
    assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    val mockData = listOf(mockPokemon()).asEntity()[0]
    assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
  }
}
