package com.amirreza.ecommercenikestore.presebtation.product_detail_fragment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amirreza.ecommercenikestore.common.base.EXTRA_PRODUCT_FROM_HOME_TO_DETAIL
import com.amirreza.ecommercenikestore.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.common.base.NikeViewModel
import com.amirreza.ecommercenikestore.domain.entity.Comment
import com.amirreza.ecommercenikestore.domain.useCases.CommentUseCase
import com.example.nikeshop.feature_shop.domain.entity.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(bundle: Bundle, private val commentUseCase: CommentUseCase): NikeViewModel() {
    private val _productLiveData = MutableLiveData<Product>()
    val productLiveData:LiveData<Product> = _productLiveData

    private val _commentsLiveData = MutableLiveData<List<Comment>>()
    val commentsLiveData:LiveData<List<Comment>> = _commentsLiveData

    init {
        _productLiveData.value = bundle.getParcelable(EXTRA_PRODUCT_FROM_HOME_TO_DETAIL)
        commentUseCase.getAll(_productLiveData.value!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable){
                override fun onSuccess(t: List<Comment>) {
                    _commentsLiveData.value = t
                }
            })


    }
}