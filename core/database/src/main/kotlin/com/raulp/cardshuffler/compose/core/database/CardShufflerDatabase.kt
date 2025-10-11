

package com.raulp.cardshuffler.compose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raulp.cardshuffler.compose.core.database.converter.AnswerHistoryConverter
import com.raulp.cardshuffler.compose.core.database.entitiy.FlashcardInfoEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.TopicEntity

@Database(
  entities = [TopicEntity::class, FlashcardInfoEntity::class],
  version = 12,
  exportSchema = true,
)
@TypeConverters(AnswerHistoryConverter::class)
abstract class CardShufflerDatabase : RoomDatabase() {

  abstract fun topicsDao(): TopicsDao
  abstract fun flashcardInfoDao(): FlashcardInfoDao
}
