package com.finalproject.cinephile.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finalproject.cinephile.data.model.Movie
import com.finalproject.cinephile.data.model.Search
import com.finalproject.cinephile.data.model.TvShow

@Database(
    entities = [Movie::class, TvShow::class, Search::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun searchDao(): SearchDao
}