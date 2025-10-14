

package com.raulp.cardshuffler.compose.core.data.di

import com.raulp.cardshuffler.compose.core.data.repository.details.FlashcardsRepository
import com.raulp.cardshuffler.compose.core.data.repository.details.FlashcardsRepositoryImpl
import com.raulp.cardshuffler.compose.core.data.repository.home.HomeRepository
import com.raulp.cardshuffler.compose.core.data.repository.home.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

  @Binds
  abstract fun bindsMainRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

  @Binds
  abstract fun bindsDetailRepository(
    detailsRepositoryImpl: FlashcardsRepositoryImpl,
  ): FlashcardsRepository
}
