package com.amirreza.ecommercenikestore.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class NikeViewModel: ViewModel(){
    protected val compositeDisposable = CompositeDisposable()
    val progressBarIndicatorLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}