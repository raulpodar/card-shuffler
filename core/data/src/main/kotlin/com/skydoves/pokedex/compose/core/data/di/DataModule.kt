package com.raulp.cardshuffler.compose.core.data.di

import com.raulp.cardshuffler.compose.core.data.repository.details.DetailsRepository
import com.raulp.cardshuffler.compose.core.data.repository.details.DetailsRepositoryImpl
import com.raulp.cardshuffler.compose.core.data.repository.home.HomeRepository
import com.raulp.cardshuffler.compose.core.data.repository.home.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

  @Binds
  fun bindsMainRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

  @Binds
  fun bindsDetailRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository
}
