package com.amirreza.ecommercenikestore.feature_store.presebtation.product_detail_fragment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_PRODUCT_FROM_HOME_TO_DETAIL
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeViewModel
import com.amirreza.ecommercenikestore.feature_store.domain.entity.Comment
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.CartUseCase
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.CommentUseCase
import com.example.nikeshop.feature_shop.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(
    bundle: Bundle,
    private val commentUseCase: CommentUseCase,
    private val cartUseCase:CartUseCase
): NikeViewModel() {
    private val _productLiveData = MutableLiveData<Product>()
    val productLiveData:LiveData<Product> = _productLiveData

    private val _commentsLiveData = MutableLiveData<List<Comment>>()
    val commentsLiveData:LiveData<List<Comment>> = _commentsLiveData

    init {
        _productLiveData.value = bundle.getParcelable(EXTRA_PRODUCT_FROM_HOME_TO_DETAIL)
        showProgressBar(true)
        commentUseCase.getAll(_productLiveData.value!!.id)
            .subscribeOn(Schedulers.io())
            .doFinally { showProgressBar(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable){
                override fun onSuccess(t: List<Comment>) {
                    _commentsLiveData.value = t
                }
            })
    }

    fun addProductToShoppingCart():Completable{
        return cartUseCase.addToCart(_productLiveData.value!!.id).ignoreElement()
    }
}