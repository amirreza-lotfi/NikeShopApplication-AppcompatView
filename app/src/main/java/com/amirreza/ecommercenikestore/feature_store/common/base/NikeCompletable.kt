package com.amirreza.ecommercenikestore.feature_store.common.base

import com.amirreza.feature_store.common.base.mappingException
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

abstract class NikeCompletable(private val compositeDisposable: CompositeDisposable):CompletableObserver {
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        EventBus.getDefault().post(mappingException(e))
    }
}