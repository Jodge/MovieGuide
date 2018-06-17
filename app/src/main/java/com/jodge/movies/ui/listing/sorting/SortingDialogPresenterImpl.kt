package com.jodge.movies.ui.listing.sorting

class SortingDialogPresenterImpl(interactor: SortingDialogInteractor) : SortingDialogPresenter {

    private var view: SortingDialogView? = null
    private var sortingDialogInteractor = interactor

    override fun setLastSavedOption() {

        val selectedOption = sortingDialogInteractor.getSelectedSortingOption()
        when(selectedOption) {
            SortType.MOST_POPULAR.value -> view?.setPopularChecked()
            SortType.HIGHEST_RATED.value -> view?.setHighestRatedChecked()
            else -> view?.setFavoritesChecked()
        }
    }

    override fun onPopularMoviesSelected() {
        sortingDialogInteractor.setSortingOption(SortType.MOST_POPULAR)
        view?.dismissDialog()
    }

    override fun onHighestRatedMoviesSelected() {
        sortingDialogInteractor.setSortingOption(SortType.HIGHEST_RATED)
        view?.dismissDialog()
    }

    override fun onFavoritesSelected() {
        sortingDialogInteractor.setSortingOption(SortType.FAVORITES)
        view?.dismissDialog()
    }

    override fun setView(view: SortingDialogView) {
        this.view = view
    }

    override fun destroy() {
        view  = null
    }

}
