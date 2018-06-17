package com.jodge.movies.ui.listing.sorting

interface SortingDialogView {

    fun setPopularChecked()

    fun setHighestRatedChecked()

    fun setFavoritesChecked()

    fun dismissDialog()
}
