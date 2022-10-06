package com.finalproject.cinephile.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.finalproject.cinephile.data.model.TvShow

@Dao
interface TvShowDao : BaseDao<TvShow> {

    @Query("SELECT * FROM favorite_tv")
    fun getFavoriteTvShows(): LiveData<List<TvShow>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_tv WHERE id=:id)")
    suspend fun isTvShowExists(id: Int): Boolean

}