package com.raulp.cardshuffler.compose.core.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

  @Binds
  @Singleton
  fun provideComposeNavigator(
    CardShufflerComposeNavigator: CardShufflerComposeNavigator,
  ): AppComposeNavigator<CardShufflerScreen>
}
