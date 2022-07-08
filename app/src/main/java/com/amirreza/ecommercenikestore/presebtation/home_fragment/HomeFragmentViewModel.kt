package com.amirreza.ecommercenikestore.presebtation.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.common.base.NikeViewModel
import com.amirreza.ecommercenikestore.domain.useCases.BannerUseCases
import com.amirreza.ecommercenikestore.domain.useCases.ProductUseCases
import com.example.nikeshop.feature_shop.domain.entity.Banner
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.example.nikeshop.feature_shop.domain.entity.SORT_NEWEST
import com.example.nikeshop.feature_shop.domain.entity.SORT_POPULAR
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeFragmentViewModel(
    private val bannerUseCases: BannerUseCases,
    private val productUseCases: ProductUseCases
):NikeViewModel(){

    private val _latestProductsLiveData = MutableLiveData<List<Product>>()
    val latestProductsLiveData: LiveData<List<Product>> = _latestProductsLiveData

    private val _bannerLiveData = MutableLiveData<List<Banner>>()
    val bannerLiveData: LiveData<List<Banner>> = _bannerLiveData

    private val _popularProductsLiveData = MutableLiveData<List<Product>>()
    val popularProductsLiveData: LiveData<List<Product>> = _popularProductsLiveData

    init {
        progressBarIndicatorLiveData.value = true
        getProductFromServer()
        getPopularProductsFromServer()
        getBannerFromServer()
    }

    private fun getProductFromServer(){
        productUseCases.getProductsUC(SORT_NEWEST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable){
                override fun onSuccess(t: List<Product>) {
                    _latestProductsLiveData.value = t
                }
            })
    }
    private fun getPopularProductsFromServer(){
        productUseCases.getProductsUC(SORT_POPULAR)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable){
                override fun onSuccess(t: List<Product>) {
                    _popularProductsLiveData.value = t
                }
            })
    }
    private fun getBannerFromServer(){
        bannerUseCases.getBannerUC()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarIndicatorLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<Banner>>(compositeDisposable){
                override fun onSuccess(t: List<Banner>) {
                    _bannerLiveData.value = t
                }
            })
    }
}