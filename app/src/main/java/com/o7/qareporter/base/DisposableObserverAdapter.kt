package com.o7.qareporter.base

import android.support.annotation.CallSuper
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

abstract class DisposableObserverAdapter<T> : DisposableObserver<T>() {

    override fun onComplete() {
        // Optional
    }

    override fun onNext(item: T) {
        // Optional
    }

    @CallSuper
    override fun onError(e: Throwable) {
        Timber.e(e)
    }
}