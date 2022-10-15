package com.finalproject.cinephile.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.finalproject.cinephile.data.model.Genre
import java.util.*

data class GenresResponse(
    @Expose @SerializedName("genres") val genres: ArrayList<Genre>
)