package com.jodge.movies.ui.listing

import com.jodge.movies.api.TheMovieDbApi
import com.jodge.movies.ui.favorites.FavoritesInteractor
import com.jodge.movies.ui.listing.sorting.SortingOptionStore
import dagger.Module
import dagger.Provides

@Module
class ListingModule {

    @Provides
    fun provideListingPresenter(listingInteractor: ListingInteractor): ListingPresenter {
        return ListingPresenterImpl(listingInteractor)
    }

    @Provides
    @ListingScope
    fun provideListingInteractor(api: TheMovieDbApi, favoritesInteractor: FavoritesInteractor, store: SortingOptionStore): ListingInteractor {
        return ListingInteractorImpl(api, favoritesInteractor, store)
    }
}
