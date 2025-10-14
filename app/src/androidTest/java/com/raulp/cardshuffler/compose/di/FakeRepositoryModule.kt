

package com.raulp.cardshuffler.compose.di

import com.raulp.cardshuffler.compose.core.data.di.DataModule
import com.raulp.cardshuffler.compose.core.data.repository.details.FakeFlashcardsRepository
import com.raulp.cardshuffler.compose.core.data.repository.details.FlashcardsRepository
import com.raulp.cardshuffler.compose.core.data.repository.home.FakeHomeRepository
import com.raulp.cardshuffler.compose.core.data.repository.home.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
  components = [SingletonComponent::class],
  replaces = [DataModule::class],
)
abstract class FakeRepositoryModule {

  @Binds
  @Singleton
  abstract fun bindFlashcardsRepository(
    fakeFlashcardsRepository: FakeFlashcardsRepository,
  ): FlashcardsRepository

  @Binds
  @Singleton
  abstract fun bindHomeRepository(fakeHomeRepository: FakeHomeRepository): HomeRepository
}
