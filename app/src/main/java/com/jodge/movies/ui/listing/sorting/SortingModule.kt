package com.jodge.movies.ui.listing.sorting

import dagger.Module
import dagger.Provides

@Module
class SortingModule {

    @Provides
    fun provideSortingDialogInteractor(store: SortingOptionStore): SortingDialogInteractor {
        return SortingDialogInteractorImpl(store)
    }

    @Provides
    fun provideSortingDialogPresenter(interactor: SortingDialogInteractor): SortingDialogPresenter {
        return SortingDialogPresenterImpl(interactor)
    }
}
