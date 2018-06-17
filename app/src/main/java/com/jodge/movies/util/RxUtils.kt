package com.jodge.movies.util

import io.reactivex.disposables.Disposable

class RxUtils {

    companion object {

        fun unsubscribe(subscription: Disposable?) {
            if (subscription != null && !subscription.isDisposed)
                subscription.dispose() // else subscription doesn't exist or already unsubscribed
        }

        fun unsubscribe(subscriptions: Array<Disposable?>) {
            for (sub in subscriptions) {
                unsubscribe(sub)
            }
        }
    }
}
