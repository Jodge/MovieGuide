package com.jodge.movies.ui.details

import com.jodge.movies.data.models.Movie
import com.jodge.movies.data.models.Review
import com.jodge.movies.data.models.Video

interface DetailsView {

    fun showDetails(movie: Movie)

    fun showTrailers(trailers: List<Video>)

    fun showReviews(reviews: List<Review>)

    fun showFavorited()

    fun showUnFavorited()
}
