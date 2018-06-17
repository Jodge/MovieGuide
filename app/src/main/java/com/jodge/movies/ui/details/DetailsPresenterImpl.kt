package com.jodge.movies.ui.details

import android.util.Log
import com.jodge.movies.ui.favorites.FavoritesInteractor
import com.jodge.movies.data.models.Movie
import com.jodge.movies.data.models.Review
import com.jodge.movies.data.models.Video
import com.jodge.movies.util.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class DetailsPresenterImpl(val interactor: DetailsInteractor, val favoritesInteractor: FavoritesInteractor): DetailsPresenter {

    private var reviewsSubscription: Disposable? = null
    private var trailerSubscription: Disposable? = null

    private var view: DetailsView? = null

    override fun setView(detailsView: DetailsView) {
        this.view = detailsView
    }

    override fun destroy() {
        view = null
        RxUtils.unsubscribe(arrayOf(reviewsSubscription, trailerSubscription))
    }

    override fun showDetails(movie: Movie) {
        view?.showDetails(movie)
    }

    override fun showReviews(movie: Movie) {
        reviewsSubscription = interactor.getReviews(movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetReviewSuccess, this::onGetFailure)
    }

    override fun showTrailers(movie: Movie) {
        trailerSubscription = interactor.getTrailers(movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTrailerSuccess, this::onGetFailure)
    }

    override fun showFavoriteButton(movie: Movie) {
        val isFavorite = favoritesInteractor.isFavorite(movie.id)
        if (isFavorite) view?.showFavorited() else view?.showUnFavorited()
    }

    override fun onFavoriteClick(movie: Movie) {
        val isFavorite = favoritesInteractor.isFavorite(movie.id)
        if (isFavorite) {
            favoritesInteractor.unFavorite(movie.id)
            view?.showUnFavorited()
        } else {
            favoritesInteractor.setFavorite(movie)
            view?.showFavorited()
        }
    }

    private fun onGetReviewSuccess(reviewsResponse: List<Review>) {
        view?.showReviews(reviewsResponse)
    }

    private fun onGetTrailerSuccess(trailerResponse: List<Video>) {
        view?.showTrailers(trailerResponse)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(DetailsPresenter::class.java.simpleName, e?.message)
    }

}
