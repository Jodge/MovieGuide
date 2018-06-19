package com.jodge.movies.data.persistence

import javax.inject.Inject

class MovieDataSource @Inject constructor(movieDao: MovieDao) : MovieRepository {

    private var movieDao: MovieDao = movieDao

    override fun findById(id: String): Movie? = movieDao.getMovieById(id)

    override fun findAll(): List<Movie> = movieDao.findAll()

    override fun deleteById(id: String) = movieDao.deleteMovieById(id)

    override fun insert(movie: Movie) = movieDao.insertMovie(movie)

}
