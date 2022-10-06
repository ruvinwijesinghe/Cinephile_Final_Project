package com.finalproject.cinephile.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.finalproject.cinephile.data.model.Image

data class ImageResponse(
    @Expose @SerializedName("backdrops") val backdrops: List<Image>
)