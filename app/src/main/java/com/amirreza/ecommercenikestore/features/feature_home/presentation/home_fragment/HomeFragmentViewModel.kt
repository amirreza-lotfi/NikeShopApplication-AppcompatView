package com.amirreza.ecommercenikestore.features.feature_home.presentation.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.FavoriteRepository
import com.amirreza.ecommercenikestore.utils.base.NikeCompletable
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall
import com.amirreza.ecommercenikestore.features.feature_home.domain.useCases.BannerUseCases
import com.amirreza.ecommercenikestore.features.feature_home.domain.useCases.ProductUseCases
import com.example.nikeshop.feature_shop.domain.entity.Banner
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.SORT_NEWEST
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.SORT_POPULAR
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeFragmentViewModel(
    private val bannerUseCases: BannerUseCases,
    private val productUseCases: ProductUseCases,
    private val favoriteRepository: FavoriteRepository,
): NikeViewModel(){

    private val _latestProductsLiveData = MutableLiveData<List<Product>>()
    val latestProductsLiveData: LiveData<List<Product>> = _latestProductsLiveData

    private val _bannerLiveData = MutableLiveData<List<Banner>>()
    val bannerLiveData: LiveData<List<Banner>> = _bannerLiveData

    private val _popularProductsLiveData = MutableLiveData<List<Product>>()
    val popularProductsLiveData: LiveData<List<Product>> = _popularProductsLiveData

    init {
        progressBarIndicatorLiveData.value = true
        getPopularProductsFromServer()
        getBannerFromServer()
    }

    fun getProductFromServer(){
        favoriteRepository.getFavoriteProducts()
            .asyncIoNetworkCall()
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable){
                override fun onSuccess(favoriteList: List<Product>) {
                    productUseCases.getProductsUC(SORT_NEWEST)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable){
                            override fun onSuccess(productList: List<Product>) {
                                for (favorite:Product in favoriteList){
                                    val id = favorite.id
                                    val findProduct = productList.find {
                                        it.id == id
                                    }
                                    findProduct?.let {
                                        it.isFavorite = true
                                    }
                                }
                                _latestProductsLiveData.value = productList
                            }
                        })
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

    fun addOrDeleteProductFromFavorite(product: Product){
        product.isFavorite = !product.isFavorite
        if(product.isFavorite){
            favoriteRepository.deleteProductToFavorites(product)
                .asyncIoNetworkCall()
                .subscribe(object : NikeCompletable(compositeDisposable){
                    override fun onComplete() {

                    }
                })
        }else{
            favoriteRepository.addProductToFavorites(product)
                .asyncIoNetworkCall()
                .subscribe(object : NikeCompletable(compositeDisposable){
                    override fun onComplete() {

                    }
                })
        }
    }
}