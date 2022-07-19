package com.amirreza.ecommercenikestore.feature_store.presebtation.all_product_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeViewModel
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.ProductUseCases
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.sevenlearn.nikestore.common.asyncIoNetworkCall

class AllProductViewModel(var sortType:Int, private val productUseCases: ProductUseCases):NikeViewModel() {
    private val _productList = MutableLiveData<List<Product>>()
    val productList:LiveData<List<Product>> = _productList

    init {
        getProducts()
    }

      private fun getProducts(){
        showProgressBar(true)
        productUseCases.getProductsUC(sortType)
            .asyncIoNetworkCall()
            .doFinally { showProgressBar(false) }
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable){
                override fun onSuccess(t: List<Product>) {
                    _productList.value = t
                }
            })
    }

    fun selectedIndexChanged(newIndex:Int){
        if(newIndex == sortType)
            return

        sortType = newIndex
        getProducts()
    }
}