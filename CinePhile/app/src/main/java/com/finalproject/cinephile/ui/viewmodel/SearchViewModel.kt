package com.finalproject.cinephile.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.finalproject.cinephile.data.Repository
import com.finalproject.cinephile.data.remote.response.MovieResponse
import com.finalproject.cinephile.data.remote.response.TvShowResponse
import com.finalproject.cinephile.data.vo.Resource

class SearchViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var query = savedStateHandle[SEARCH_QUERY_KEY] ?: ""

    companion object {
        const val TAG = "SearchVM"
        const val SEARCH_QUERY_KEY = "SEARCH_QUERY"
    }
}