package com.jodge.movies.ui.favorites

import com.jodge.movies.data.models.Movie
import com.jodge.movies.data.persistence.MovieRepository
import kotlin.properties.Delegates

class FavoritesInteractorImpl constructor(repository: MovieRepository) : FavoritesInteractor {

    var repository : MovieRepository by Delegates.notNull()

    init {
        this.repository = repository
    }

    override fun setFavorite(movie: Movie) {
        with(movie) {
            val dbMovie = com.jodge.movies.data.persistence.Movie(id, voteAverage,
                    title, posterPath, backdropPath, overview, releaseDate)
            repository.insert(dbMovie)
        }
    }

    override fun isFavorite(id: String): Boolean {
        val movie = repository.findById(id)
        return movie != null
    }

    override fun getFavorites(): List<Movie> {
        val dbMovies = repository.findAll()
        return dbMovies.map {
            with(it) {
                Movie(id, voteAverage, title, posterPath, backdropPath, overview, releaseDate)
            }
        }
    }

    override fun unFavorite(id: String) {
        repository.deleteById(id)
    }

    companion object {
        // private val TAG = FavoritesInteractor::class.java.name
    }
}
