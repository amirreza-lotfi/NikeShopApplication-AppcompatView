package com.amirreza.ecommercenikestore.features.feature_home.presentation.product_detail_fragment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.utils.util.EXTRA_PRODUCT_FROM_HOME_TO_DETAIL
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Comment
import com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.CartUseCase
import com.amirreza.ecommercenikestore.features.feature_home.domain.useCases.CommentUseCase
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(
    bundle: Bundle,
    private val commentUseCase: CommentUseCase,
    private val cartUseCase: CartUseCase
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