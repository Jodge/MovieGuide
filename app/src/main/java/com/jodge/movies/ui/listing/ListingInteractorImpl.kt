package com.jodge.movies.ui.listing

import com.jodge.movies.api.TheMovieDbApi
import com.jodge.movies.ui.favorites.FavoritesInteractor
import com.jodge.movies.ui.listing.sorting.SortType
import com.jodge.movies.ui.listing.sorting.SortingOptionStore
import com.jodge.movies.data.models.Movie
import io.reactivex.Observable

class ListingInteractorImpl(val movieDbApi: TheMovieDbApi,
                            interactor: FavoritesInteractor, store: SortingOptionStore) : ListingInteractor {

    private val sortingOptionStore = store
    private val favoritesInteractor = interactor

    override fun fetchMovies(): Observable<List<Movie>> {
        val selectedOption = sortingOptionStore.getSelectedOption()
        return when (selectedOption) {
            SortType.MOST_POPULAR.value -> {
                val observable = movieDbApi.popularMovies(hashMapOf(
                        "language" to "en",
                        "sort_by" to "popularity.desc"
                        )
                )
                observable.map { t -> t.data }
            }
            SortType.HIGHEST_RATED.value -> {
                val observable = movieDbApi.highestRatedMovies(hashMapOf(
                        "vote_count.gte" to "500",
                        "language" to "en",
                        "sort_by" to "vote_average.desc"
                        )
                )
                observable.map { t -> t.data }
            }
            else -> {
                Observable.just(favoritesInteractor.getFavorites())
            }
        }
    }
}