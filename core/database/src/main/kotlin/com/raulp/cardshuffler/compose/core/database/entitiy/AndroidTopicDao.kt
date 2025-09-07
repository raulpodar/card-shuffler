package com.raulp.cardshuffler.compose.core.database.entitiy

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AndroidTopicDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAndroidTopics(pokemonList: List<AndroidTopicEntity>)

  @Query("SELECT * FROM TopicEntity")
  suspend fun getAndroidTopics(): List<AndroidTopicEntity>

  @Query("SELECT * FROM TopicEntity")
  suspend fun getAllAndroidTopics(): List<AndroidTopicEntity>
}
