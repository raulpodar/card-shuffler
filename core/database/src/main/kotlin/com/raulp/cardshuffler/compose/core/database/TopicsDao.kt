

package com.raulp.cardshuffler.compose.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raulp.cardshuffler.compose.core.database.entitiy.TopicEntity

@Dao
interface TopicsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTopicsList(topicList: List<TopicEntity>)

  @Query("SELECT * FROM TopicEntity")
  suspend fun getTopicsList(): List<TopicEntity>

  @Query("SELECT * FROM TopicEntity")
  suspend fun getAllTopicsList(): List<TopicEntity>

  @Query("DELETE FROM TopicEntity")
  suspend fun clearTopics()
}
