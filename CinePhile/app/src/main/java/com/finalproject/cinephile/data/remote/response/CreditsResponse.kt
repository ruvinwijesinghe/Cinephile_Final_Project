package com.finalproject.cinephile.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.finalproject.cinephile.data.model.Cast

data class CreditsResponse(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("cast") val cast: List<Cast>
)