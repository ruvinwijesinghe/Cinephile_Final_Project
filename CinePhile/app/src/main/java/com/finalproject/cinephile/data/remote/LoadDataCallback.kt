package com.finalproject.cinephile.data.remote

interface LoadDataCallback<T> {
    fun onDataReceived(data: T)
    fun onDataNotAvailable()
}