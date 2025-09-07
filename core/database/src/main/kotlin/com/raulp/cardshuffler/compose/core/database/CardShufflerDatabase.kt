

package com.raulp.cardshuffler.compose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raulp.cardshuffler.compose.core.database.entitiy.AndroidTopicDao
import com.raulp.cardshuffler.compose.core.database.entitiy.AndroidTopicEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.PokemonInfoEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.TopicEntity

@Database(
  entities = [TopicEntity::class, PokemonInfoEntity::class, AndroidTopicEntity::class],
  version = 7,
  exportSchema = true,
)
@TypeConverters(value = [TypeResponseConverter::class, StatsResponseConverter::class])
abstract class CardShufflerDatabase : RoomDatabase() {

  abstract fun pokemonDao(): TopicsDao
  abstract fun pokemonInfoDao(): PokemonInfoDao
  abstract fun androidTopicDao(): AndroidTopicDao
}
