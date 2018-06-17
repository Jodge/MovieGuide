package com.jodge.movies.ui.listing

import com.jodge.movies.data.models.Movie
import io.reactivex.Observable

interface ListingInteractor {
    fun fetchMovies() : Observable<List<Movie>>
}
