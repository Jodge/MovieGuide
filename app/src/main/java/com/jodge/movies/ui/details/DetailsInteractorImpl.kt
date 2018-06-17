package com.jodge.movies.ui.details

import com.jodge.movies.api.TheMovieDbApi
import com.jodge.movies.data.models.Review
import com.jodge.movies.data.models.Video
import io.reactivex.Observable

class DetailsInteractorImpl(val movieDbApi: TheMovieDbApi): DetailsInteractor {

    override fun getTrailers(id: String): Observable<List<Video>> {
        return movieDbApi.getTrailers(id).map { it -> it.data }
    }

    override fun getReviews(id: String): Observable<List<Review>> {
        return movieDbApi.getReviews(id).map { it -> it.data }
    }
}