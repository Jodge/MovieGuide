package com.jodge.movies.ui.details

import com.jodge.movies.api.TheMovieDbApi
import com.jodge.movies.ui.favorites.FavoritesInteractor
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

    @Provides @DetailsScope
    fun provideDetailsPresenter(detailsInteractor: DetailsInteractor, favoritesInteractor: FavoritesInteractor): DetailsPresenter {
        return DetailsPresenterImpl(detailsInteractor, favoritesInteractor)
    }

    @Provides @DetailsScope
    fun provideDetailsInteractor(api: TheMovieDbApi): DetailsInteractor {
        return DetailsInteractorImpl(api)
    }
}
