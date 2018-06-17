package com.jodge.movies.data.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context) : MovieDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }


        fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java, "movie.db").allowMainThreadQueries()
                        .build()
    }
}
