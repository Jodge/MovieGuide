package com.jodge.movies.ui.listing

import com.jodge.movies.data.models.Movie


interface ListingView {

    fun showMovies(movies: List<Movie>)

    fun loadingStarted()

    fun loadingFailed(errorMessage: String)
}
