package com.jodge.movies.ui.favorites

import com.jodge.movies.data.models.Movie

interface FavoritesInteractor {

    fun setFavorite(movie: Movie)

    fun isFavorite(id: String): Boolean

    fun getFavorites(): List<Movie>

    fun unFavorite(id: String)

}
