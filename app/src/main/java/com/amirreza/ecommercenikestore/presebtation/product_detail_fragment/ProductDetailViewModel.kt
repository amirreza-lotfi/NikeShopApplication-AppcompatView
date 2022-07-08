package com.amirreza.ecommercenikestore.presebtation.product_detail_fragment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amirreza.ecommercenikestore.base.EXTRA_PRODUCT_FROM_HOME_TO_DETAIL
import com.example.nikeshop.feature_shop.domain.entity.Product

class ProductDetailViewModel(bundle: Bundle): ViewModel() {
    private val _productLiveData = MutableLiveData<Product>()
    val productLiveData:LiveData<Product> = _productLiveData

    init {
        _productLiveData.value = bundle.getParcelable(EXTRA_PRODUCT_FROM_HOME_TO_DETAIL)

    }
}