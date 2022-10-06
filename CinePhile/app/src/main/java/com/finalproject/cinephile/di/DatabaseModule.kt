package com.finalproject.cinephile.di

import android.content.Context
import androidx.room.Room
import com.finalproject.cinephile.data.local.database.AppDatabase
import com.finalproject.cinephile.data.local.database.MovieDao
import com.finalproject.cinephile.data.local.database.SearchDao
import com.finalproject.cinephile.data.local.database.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie_catalogue_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao = database.movieDao()

    @Singleton
    @Provides
    fun provideTvShowDao(database: AppDatabase): TvShowDao = database.tvShowDao()

    @Singleton
    @Provides
    fun provideSearchDao(database: AppDatabase): SearchDao = database.searchDao()
}