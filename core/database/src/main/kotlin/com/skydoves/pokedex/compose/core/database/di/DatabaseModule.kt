

package com.raulp.cardshuffler.compose.core.database.di

import android.app.Application
import androidx.room.Room
import com.raulp.cardshuffler.compose.core.database.PokedexDatabase
import com.raulp.cardshuffler.compose.core.database.PokemonDao
import com.raulp.cardshuffler.compose.core.database.PokemonInfoDao
import com.raulp.cardshuffler.compose.core.database.StatsResponseConverter
import com.raulp.cardshuffler.compose.core.database.TypeResponseConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application,
    typeResponseConverter: TypeResponseConverter,
    statsResponseConverter: StatsResponseConverter,
  ): PokedexDatabase {
    return Room
      .databaseBuilder(application, PokedexDatabase::class.java, "Pokedex.db")
      .fallbackToDestructiveMigration()
      .addTypeConverter(typeResponseConverter)
      .addTypeConverter(statsResponseConverter)
      .build()
  }

  @Provides
  @Singleton
  fun providePokemonDao(appDatabase: PokedexDatabase): PokemonDao {
    return appDatabase.pokemonDao()
  }

  @Provides
  @Singleton
  fun providePokemonInfoDao(appDatabase: PokedexDatabase): PokemonInfoDao {
    return appDatabase.pokemonInfoDao()
  }

  @Provides
  @Singleton
  fun provideTypeResponseConverter(json: Json): TypeResponseConverter {
    return TypeResponseConverter(json)
  }
}
