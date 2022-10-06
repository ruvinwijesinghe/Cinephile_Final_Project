package com.finalproject.cinephile.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.finalproject.cinephile.data.Repository
import com.finalproject.cinephile.data.remote.response.MovieResponse
import com.finalproject.cinephile.data.vo.Resource

class MoviesViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val popularMovies: LiveData<Resource<MovieResponse>> by lazy {
        repository.getMovies("popular")
    }

    val nowPlayingMovies: LiveData<Resource<MovieResponse>> by lazy {
        repository.getMovies("now_playing")
    }

    val topRatedMovies: LiveData<Resource<MovieResponse>> by lazy {
        repository.getMovies("top_rated")
    }

    val newReleaseMovies: LiveData<MovieResponse> by lazy {
        repository.getNewReleaseMovies()
    }

    companion object {
        private const val TAG = "MoviesVM"
    }

}