package com.finalproject.cinephile.data.remote.response

import com.google.gson.annotations.Expose
import com.finalproject.cinephile.data.model.ContentRating

data class ContentRatingResponse(
    @Expose val results: List<ContentRating>
)