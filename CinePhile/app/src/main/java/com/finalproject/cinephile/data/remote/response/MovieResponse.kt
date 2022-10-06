package com.finalproject.cinephile.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.finalproject.cinephile.data.model.Movie

data class MovieResponse(
    @Expose @SerializedName("page") val page: Int,
    @Expose @SerializedName("results") val movieItems: List<Movie>,
    @Expose @SerializedName("total_pages") val totalPages: Int,
    @Expose @SerializedName("total_results") val totalResults: Int
)