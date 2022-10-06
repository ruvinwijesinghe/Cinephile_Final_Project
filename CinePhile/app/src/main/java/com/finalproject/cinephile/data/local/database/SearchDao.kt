package com.finalproject.cinephile.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.finalproject.cinephile.data.model.Search

@Dao
interface SearchDao : BaseDao<Search> {

    @Query("SELECT * FROM query_search ORDER BY id DESC")
    fun getSearchQueries(): LiveData<List<Search>>

    @Query("SELECT `query` FROM query_search WHERE `query`=:query")
    suspend fun selectSearchQuery(query: String): String

    @Query("DELETE FROM query_search")
    suspend fun deleteSearchQueries()

}