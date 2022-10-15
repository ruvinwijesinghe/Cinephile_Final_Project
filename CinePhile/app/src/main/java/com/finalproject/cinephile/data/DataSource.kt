package com.finalproject.cinephile.data

import androidx.lifecycle.LiveData
import com.finalproject.cinephile.data.model.Movie
import com.finalproject.cinephile.data.model.Search
import com.finalproject.cinephile.data.model.Season
import com.finalproject.cinephile.data.model.TvShow
import com.finalproject.cinephile.data.remote.response.MovieResponse
import com.finalproject.cinephile.data.remote.response.TvShowResponse
import com.finalproject.cinephile.data.vo.Resource

interface DataSource {
    fun getMovies(category: String): LiveData<Resource<MovieResponse>>

    fun searchMovies(query: String): LiveData<Resource<MovieResponse>>

    fun getNewReleaseMovies(): LiveData<MovieResponse>

    fun getMovieDetails(movieId: Int): LiveData<Resource<Movie>>

    

    fun searchTvShows(query: String): LiveData<Resource<TvShowResponse>>

    fun getNewReleaseTvShows(): LiveData<TvShowResponse>

    fun getTvShowDetails(tvShowId: Int): LiveData<Resource<TvShow>>

    fun getSeasonDetails(tvId: Int, seasonNumber: Int): LiveData<Season>

    fun getFavoriteMovies(): LiveData<List<Movie>>

    suspend fun addFavoriteMovie(movie: Movie)

    suspend fun removeFavoriteMovie(movie: Movie)

    suspend fun isFavoriteMovie(movieId: Int): Boolean

    fun getFavoriteTvShows(): LiveData<List<TvShow>>

    suspend fun addFavoriteTvShow(tvShow: TvShow)

    suspend fun removeFavoriteTvShow(tvShow: TvShow)

    suspend fun isFavoriteTvShow(tvShowId: Int): Boolean

    fun getSearchHistories(): LiveData<List<Search>>

    suspend fun addSearchQuery(search: Search)

    suspend fun removeSearchQuery(search: Search)

    suspend fun removeSearchHistories()

    suspend fun selectSearchQuery(query: String): String?
}