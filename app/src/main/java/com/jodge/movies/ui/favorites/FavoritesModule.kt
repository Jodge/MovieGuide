package com.jodge.movies.ui.favorites

import com.jodge.movies.data.persistence.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoritesModule {

    @Provides
    @Singleton
    fun provideFavoritesInteractor(repository: MovieRepository) : FavoritesInteractor {
        return FavoritesInteractorImpl(repository)
    }

}
