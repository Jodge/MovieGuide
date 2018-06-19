package com.jodge.movies.data.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: String) : Movie?

    @Query("SELECT * FROM movies")
    fun findAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("DELETE FROM movies WHERE id = :id")
    fun deleteMovieById(id: String)
}