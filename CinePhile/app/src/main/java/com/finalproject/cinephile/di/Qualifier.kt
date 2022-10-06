package com.finalproject.cinephile.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MovieQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TvShowQualifier