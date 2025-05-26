

package com.raulp.cardshuffler.compose.core.network.di

import com.raulp.cardshuffler.compose.core.network.service.CardShufflerClient
import com.raulp.cardshuffler.compose.core.network.service.CardShufflerService
import com.raulp.cardshuffler.core.network.BuildConfig
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

  @Singleton
  @Provides
  fun provideJson(): Json = Json {
    ignoreUnknownKeys = true
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .apply {
      if (BuildConfig.DEBUG) {
        this.addNetworkInterceptor(
          HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
          },
        )
      }
    }
    .build()

  @Provides
  @Singleton
  fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
    .build()

  @Provides
  @Singleton
  fun provideCardShufflerService(retrofit: Retrofit): CardShufflerService =
    retrofit.create(CardShufflerService::class.java)

  @Provides
  @Singleton
  fun provideCardShufflerClient(CardShufflerService: CardShufflerService): CardShufflerClient =
    CardShufflerClient(CardShufflerService)
}
