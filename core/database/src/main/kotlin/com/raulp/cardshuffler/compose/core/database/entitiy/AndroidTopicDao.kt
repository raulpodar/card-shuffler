package com.raulp.cardshuffler.compose.core.database.entitiy

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AndroidTopicDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAndroidTopics(pokemonList: List<AndroidTopicEntity>)

  @Query("SELECT * FROM PokemonEntity WHERE page = :page_")
  suspend fun getAndroidTopics(page_: Int): List<AndroidTopicEntity>

  @Query("SELECT * FROM PokemonEntity WHERE page <= :page_")
  suspend fun getAllAndroidTopics(page_: Int): List<AndroidTopicEntity>
}
