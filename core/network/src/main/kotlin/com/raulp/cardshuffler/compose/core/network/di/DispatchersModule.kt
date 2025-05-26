

package com.raulp.cardshuffler.compose.core.network.di

import com.raulp.cardshuffler.compose.core.network.CardShufflerAppDispatchers
import com.raulp.cardshuffler.compose.core.network.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

  @Provides
  @Dispatcher(cardShufflerAppDispatchers = CardShufflerAppDispatchers.IO)
  fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
