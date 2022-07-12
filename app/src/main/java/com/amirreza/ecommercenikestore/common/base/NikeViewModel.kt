package com.amirreza.ecommercenikestore.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class NikeViewModel: ViewModel(){
    protected val compositeDisposable = CompositeDisposable()
    val progressBarIndicatorLiveData = MutableLiveData<Boolean>()

    protected fun showProgressBar(b: Boolean) {
        progressBarIndicatorLiveData.value = b
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}