package com.finalproject.cinephile.data.local

import com.finalproject.cinephile.data.local.database.MovieDao
import com.finalproject.cinephile.data.local.database.SearchDao
import com.finalproject.cinephile.data.local.database.TvShowDao
import com.finalproject.cinephile.data.model.Movie
import com.finalproject.cinephile.data.model.Search
import com.finalproject.cinephile.data.model.TvShow
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao,
    private val searchDao: SearchDao
) {

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    suspend fun addMovie(movie: Movie) = movieDao.insert(movie)

    suspend fun removeMovie(movie: Movie) = movieDao.delete(movie)

    suspend fun isMovieExists(id: Int): Boolean = movieDao.isMovieExists(id)

    fun getFavoriteTvShows() = tvShowDao.getFavoriteTvShows()

    suspend fun addTvShow(tvShow: TvShow) = tvShowDao.insert(tvShow)

    suspend fun removeTvShow(tvShow: TvShow) = tvShowDao.delete(tvShow)

    suspend fun isTvShowExists(id: Int): Boolean = tvShowDao.isTvShowExists(id)

    fun getSearchHistories() = searchDao.getSearchQueries()

    suspend fun addSearchQuery(search: Search) = searchDao.insert(search)

    suspend fun removeSearchQuery(search: Search) = searchDao.delete(search)

    suspend fun removeSearchHistories() = searchDao.deleteSearchQueries()

    suspend fun getSearch(query: String): String? = searchDao.selectSearchQuery(query)

}