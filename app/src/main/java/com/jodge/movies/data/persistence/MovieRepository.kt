package com.jodge.movies.data.persistence

interface MovieRepository {

    fun findById(id: String) : Movie?

    fun deleteById(id: String)

    fun insert(movie: Movie)

    fun findAll() : List<Movie>
}
