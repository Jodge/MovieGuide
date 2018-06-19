package com.jodge.movies.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides  @Singleton @Named("BASE_URL")
    fun provideBaseUrl(): String = "http://api.themoviedb.org"

    @Provides  @Singleton
    fun provideMovieDbApi(retrofit: Retrofit): TheMovieDbApi =  retrofit.create(TheMovieDbApi::class.java)
}
