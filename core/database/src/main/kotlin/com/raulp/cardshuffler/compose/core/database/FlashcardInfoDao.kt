package com.raulp.cardshuffler.compose.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raulp.cardshuffler.compose.core.database.entitiy.FlashcardInfoEntity

@Dao
interface FlashcardInfoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemonInfo(pokemonInfo: FlashcardInfoEntity)

  @Query("SELECT * FROM FlashcardInfoEntity")
  suspend fun getPokemonInfo(): FlashcardInfoEntity?
}
