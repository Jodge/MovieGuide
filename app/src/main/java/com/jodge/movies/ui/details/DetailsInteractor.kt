package com.jodge.movies.ui.details

import com.jodge.movies.data.models.Review
import com.jodge.movies.data.models.Video
import io.reactivex.Observable

interface DetailsInteractor {

    fun getTrailers(id: String) : Observable<List<Video>>

    fun getReviews(id: String) : Observable<List<Review>>
}
