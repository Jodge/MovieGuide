package com.jodge.movies.ui.listing.sorting

class SortingDialogInteractorImpl(store: SortingOptionStore): SortingDialogInteractor {

    private var sortingOptionStore: SortingOptionStore = store

    override fun getSelectedSortingOption(): Int {
        return sortingOptionStore.getSelectedOption()
    }

    override fun setSortingOption(sortType: SortType) {
        sortingOptionStore.setSelectOption(sortType)
    }
}
