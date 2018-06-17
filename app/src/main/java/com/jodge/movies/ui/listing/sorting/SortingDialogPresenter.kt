package com.jodge.movies.ui.listing.sorting

interface SortingDialogPresenter {

    fun setLastSavedOption()

    fun onPopularMoviesSelected()

    fun onHighestRatedMoviesSelected()

    fun onFavoritesSelected()

    fun setView(view: SortingDialogView)

    fun destroy()

}
