package com.jodge.movies.data.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey @ColumnInfo(name = "id")val id: String,
        @ColumnInfo(name = "vote_average") val voteAverage: Double,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "poster_path") val posterPath: String,
        @ColumnInfo(name = "backdrop_path") val backdropPath: String,
        @ColumnInfo(name = "overview") val overview: String,
        @ColumnInfo(name = "release_date") val releaseDate: String
)