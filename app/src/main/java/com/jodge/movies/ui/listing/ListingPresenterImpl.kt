package com.jodge.movies.ui.listing

import android.util.Log
import com.jodge.movies.data.models.Movie
import com.jodge.movies.util.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListingPresenterImpl(val interactor: ListingInteractor) : ListingPresenter {

    private var view: ListingView? = null
    private var movieSubscription: Disposable? = null

    override fun setView(listingView: ListingView) {
        view = listingView
        displayMovies()
    }

    override fun destroy() {
        view = null
        RxUtils.unsubscribe(movieSubscription)
    }

    override fun displayMovies() {
        showLoading()
        movieSubscription =  interactor.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieFetchSuccess, this::onMovieFetchFailed)
    }

    private fun onMovieFetchFailed(e: Throwable) {
        Log.e(TAG, e.message)
        view?.loadingFailed("Internet connection issue")
    }

    private fun onMovieFetchSuccess(popularMoviesResponse: List<Movie>) {
        view?.showMovies(popularMoviesResponse)
    }

    private fun showLoading() {
        view?.loadingStarted()
    }

    companion object {
        val TAG  = ListingPresenter::class.java.name
    }
}
