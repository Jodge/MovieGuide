package com.jodge.movies.ui.listing

interface ListingPresenter {

    fun setView(listingView: ListingView)

    fun displayMovies()

    fun destroy()
}
