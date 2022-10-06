package com.finalproject.cinephile.data.model

import com.google.gson.annotations.Expose

data class Video(
    @Expose val key: String,
    @Expose val name: String
)