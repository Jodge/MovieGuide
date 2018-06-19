package com.jodge.movies.di

import android.content.Context
import com.jodge.movies.data.persistence.MovieDao
import com.jodge.movies.data.persistence.MovieDataSource
import com.jodge.movies.data.persistence.MovieDatabase
import com.jodge.movies.data.persistence.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideMovieDataSource(context: Context) : MovieDao = MovieDatabase.getInstance(context).movieDao()

    @Singleton
    @Provides
    fun movieRepository(movieDao: MovieDao): MovieRepository =  MovieDataSource(movieDao)
}