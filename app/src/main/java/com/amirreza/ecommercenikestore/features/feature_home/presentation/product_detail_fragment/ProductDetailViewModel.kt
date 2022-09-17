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
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.SORT_NEWEST
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.FavoriteRepository
import com.amirreza.ecommercenikestore.utils.base.NikeCompletable
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(
    bundle: Bundle,
    private val commentUseCase: CommentUseCase,
    private val cartUseCase: CartUseCase,
    private val favoriteRepository: FavoriteRepository
): NikeViewModel() {
    private val _productLiveData = MutableLiveData<Product>()
    val productLiveData:LiveData<Product> = _productLiveData

    private val _commentsLiveData = MutableLiveData<List<Comment>>()
    val commentsLiveData:LiveData<List<Comment>> = _commentsLiveData

    private val _isFavoriteLiveData = MutableLiveData(false)
    val isFavorite:LiveData<Boolean> = _isFavoriteLiveData

    init {
        _productLiveData.value = bundle.getParcelable(EXTRA_PRODUCT_FROM_HOME_TO_DETAIL)
        _isFavoriteLiveData.value = _productLiveData.value?.isFavorite ?: false
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

    fun addOrDeleteProductFromFavorite(){
        _productLiveData.value?.let { product->
            if(isFavorite.value == true){
                favoriteRepository.deleteProductToFavorites(product)
                    .asyncIoNetworkCall()
                    .subscribe(object : NikeCompletable(compositeDisposable){
                        override fun onComplete() {
                            product.isFavorite = !product.isFavorite
                            _isFavoriteLiveData.postValue(product.isFavorite)
                        }
                    })
            }else{
                favoriteRepository.addProductToFavorites(product)
                    .asyncIoNetworkCall()
                    .subscribe(object : NikeCompletable(compositeDisposable){
                        override fun onComplete() {
                            product.isFavorite = !product.isFavorite
                            _isFavoriteLiveData.postValue(product.isFavorite)
                        }
                    })
            }
        }

    }

}