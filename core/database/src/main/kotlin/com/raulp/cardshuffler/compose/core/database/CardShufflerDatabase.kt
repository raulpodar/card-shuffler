

package com.raulp.cardshuffler.compose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raulp.cardshuffler.compose.core.database.entitiy.AndroidTopicDao
import com.raulp.cardshuffler.compose.core.database.entitiy.AndroidTopicEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.PokemonEntity
import com.raulp.cardshuffler.compose.core.database.entitiy.PokemonInfoEntity

@Database(
  entities = [PokemonEntity::class, PokemonInfoEntity::class, AndroidTopicEntity::class],
  version = 5,
  exportSchema = true,
)
@TypeConverters(value = [TypeResponseConverter::class, StatsResponseConverter::class])
abstract class CardShufflerDatabase : RoomDatabase() {

  abstract fun pokemonDao(): PokemonDao
  abstract fun pokemonInfoDao(): PokemonInfoDao
  abstract fun androidTopicDao(): AndroidTopicDao
}
