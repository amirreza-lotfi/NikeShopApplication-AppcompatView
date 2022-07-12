package com.amirreza.ecommercenikestore.presebtation.all_product_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.common.base.NikeViewModel
import com.amirreza.ecommercenikestore.domain.useCases.ProductUseCases
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.sevenlearn.nikestore.common.asyncIoNetworkCall

class AllProductViewModel(private val sortType:Int, private val productUseCases: ProductUseCases):NikeViewModel() {
    private val _productList = MutableLiveData<List<Product>>()
    val productList:LiveData<List<Product>> = _productList

    init {
        getProducts()
    }


    fun getProducts(){
        showProgressBar(true)
        productUseCases.getProductsUC(sortType)
            .asyncIoNetworkCall()
            .doFinally { showProgressBar(true) }
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable){
                override fun onSuccess(t: List<Product>) {
                    _productList.value = t
                }
            })
    }
}