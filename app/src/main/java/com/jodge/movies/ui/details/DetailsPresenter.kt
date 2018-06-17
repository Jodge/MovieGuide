package com.jodge.movies.ui.details

import com.jodge.movies.data.models.Movie


interface DetailsPresenter {

    fun showDetails(movie: Movie)

    fun showTrailers(movie: Movie)

    fun showReviews(movie: Movie)

    fun showFavoriteButton(movie: Movie)

    fun onFavoriteClick(movie: Movie)

    fun destroy()

    fun setView(detailsView: DetailsView)
}