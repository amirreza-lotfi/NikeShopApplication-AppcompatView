package com.amirreza.ecommercenikestore.features.feature_store.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class NikeViewModel: ViewModel(){
    protected val compositeDisposable = CompositeDisposable()
    val progressBarIndicatorLiveData = MutableLiveData<Boolean>()

    protected fun showProgressBar(b: Boolean) {
        progressBarIndicatorLiveData.postValue(b)
    }
    protected fun showProgressBarWhenInBackgroundThread(b:Boolean){
        progressBarIndicatorLiveData.postValue(b)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}