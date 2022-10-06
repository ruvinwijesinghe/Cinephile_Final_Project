package com.finalproject.cinephile.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.finalproject.cinephile.data.model.Similar

data class SimilarResponse(
    @Expose @SerializedName("page") val page: Int,
    @Expose @SerializedName("results") val similar: List<Similar>,
    @Expose @SerializedName("total_pages") val totalPages: Int,
    @Expose @SerializedName("total_results") val totalResults: Int

)