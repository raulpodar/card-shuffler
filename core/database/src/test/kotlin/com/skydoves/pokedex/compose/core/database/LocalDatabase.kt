package com.raulp.cardshuffler.compose.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
abstract class LocalDatabase {
  lateinit var db: CardShufflerDatabase

  @Before
  fun initDB() {
    val json = Json { ignoreUnknownKeys = true }
    db = Room.inMemoryDatabaseBuilder(getApplicationContext(), CardShufflerDatabase::class.java)
      .allowMainThreadQueries()
      .addTypeConverter(TypeResponseConverter(json))
      .build()
  }

  @After
  fun closeDB() {
    db.close()
  }
}
