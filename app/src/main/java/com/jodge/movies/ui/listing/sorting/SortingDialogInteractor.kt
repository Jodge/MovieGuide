package com.jodge.movies.ui.listing.sorting

interface SortingDialogInteractor {

    fun getSelectedSortingOption(): Int

    fun setSortingOption(sortType: SortType)
}
