package com.raulp.cardshuffler.compose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raulp.cardshuffler.compose.core.database.entitiy.FlashcardInfoEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.TopicEntity

@Database(
  entities = [TopicEntity::class, FlashcardInfoEntity::class],
  version = 10,
  exportSchema = true,
)
abstract class CardShufflerDatabase : RoomDatabase() {

  abstract fun topicsDao(): TopicsDao
  abstract fun flashcardInfoDao(): FlashcardInfoDao
}
