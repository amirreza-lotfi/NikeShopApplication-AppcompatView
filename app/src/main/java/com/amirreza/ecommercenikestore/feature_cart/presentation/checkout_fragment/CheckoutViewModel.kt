package com.amirreza.ecommercenikestore.feature_cart.presentation.checkout_fragment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.PurchaseDetail
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_PURCHASE_DETAIL



class CheckoutViewModel(bundle: Bundle):ViewModel() {

    private val _purchaseDetailOfCart = MutableLiveData<PurchaseDetail>()
    val purchaseDetailOfCart: LiveData<PurchaseDetail> = _purchaseDetailOfCart

    init {
        _purchaseDetailOfCart.value = bundle.getParcelable(EXTRA_PURCHASE_DETAIL)

    }
}