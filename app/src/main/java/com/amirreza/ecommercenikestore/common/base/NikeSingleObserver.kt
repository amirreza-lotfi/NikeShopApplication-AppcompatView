package com.amirreza.ecommercenikestore.common.base

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class NikeSingleObserver<T>(private val compositeDisposable: CompositeDisposable):SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }
    override fun onError(e: Throwable) {
        Timber.e(e)
    }
}