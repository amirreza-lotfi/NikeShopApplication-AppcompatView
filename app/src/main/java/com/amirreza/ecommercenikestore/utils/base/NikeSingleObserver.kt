package com.amirreza.ecommercenikestore.utils.base

import com.amirreza.feature_store.common.base.mappingException
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

abstract class NikeSingleObserver<T>(private val compositeDisposable: CompositeDisposable):SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }
    override fun onError(e: Throwable) {
        EventBus.getDefault().post(mappingException(e))
    }
}