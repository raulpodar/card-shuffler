

package com.raulp.cardshuffler.compose.core.database.di

import android.app.Application
import androidx.room.Room
import com.raulp.cardshuffler.compose.core.database.CardShufflerDatabase
import com.raulp.cardshuffler.compose.core.database.FlashcardInfoDao
import com.raulp.cardshuffler.compose.core.database.TopicsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): CardShufflerDatabase = Room
    .databaseBuilder(application, CardShufflerDatabase::class.java, "CardShuffler.db")
    .fallbackToDestructiveMigration()
    .build()

  @Provides
  @Singleton
  fun providePokemonDao(appDatabase: CardShufflerDatabase): TopicsDao = appDatabase.topicsDao()

  @Provides
  @Singleton
  fun providePokemonInfoDao(appDatabase: CardShufflerDatabase): FlashcardInfoDao =
    appDatabase.flashcardInfoDao()
}
