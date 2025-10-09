

package com.raulp.cardshuffler.compose.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raulp.cardshuffler.compose.core.database.entitiy.FlashcardInfoEntity

@Dao
interface FlashcardInfoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFlashcard(flashcardInfoEntity: FlashcardInfoEntity)

  @Insert
  suspend fun insertFlashcardsList(flashcardInfoEntityList: List<FlashcardInfoEntity>)

  @Query("SELECT * FROM FlashcardInfoEntity")
  suspend fun getAllFlashcards(): FlashcardInfoEntity?
}
