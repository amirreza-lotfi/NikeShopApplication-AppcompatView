package com.amirreza.ecommercenikestore.features.feature_profile.presentation.favorites_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.FavoriteRepository
import com.amirreza.ecommercenikestore.utils.base.NikeCompletable
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository): NikeViewModel() {
    private val _favoriteProducts= MutableLiveData<List<Product>>()
    val favoriteProducts: LiveData<List<Product>> get() = _favoriteProducts

    init {
        favoriteRepository.getFavoriteProducts()
            .asyncIoNetworkCall()
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable){
                override fun onSuccess(t: List<Product>) {
                    _favoriteProducts.postValue(t)
                }
            })
    }

    fun removeFromFavorite(product: Product, size:Int){
        favoriteRepository.deleteProductToFavorites(product)
            .asyncIoNetworkCall()
            .subscribe(object : NikeCompletable(compositeDisposable){
                override fun onComplete() {
                    if (size == 0) _favoriteProducts.postValue(arrayListOf())
                }
            })
    }
}