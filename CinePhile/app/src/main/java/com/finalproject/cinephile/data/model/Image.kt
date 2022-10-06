package com.finalproject.cinephile.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("file_path") @Expose val filePath: String,
    @SerializedName("vote_average") @Expose val rating: Double
)