package com.amirreza.ecommercenikestore.features.feature_cart.presentation.checkout_fragment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.OrderResult
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.PurchaseDetail
import com.amirreza.ecommercenikestore.features.feature_cart.domain.repository.OrderRepository
import com.amirreza.ecommercenikestore.utils.util.EXTRA_PURCHASE_DETAIL
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall


class CheckoutViewModel(bundle: Bundle, private val orderRepository: OrderRepository):
    NikeViewModel() {

    private val _purchaseDetailOfCart = MutableLiveData<PurchaseDetail>()
    val purchaseDetailOfCart: LiveData<PurchaseDetail> = _purchaseDetailOfCart

    fun registerOrder(
        firstName: String,
        lastname: String,
        postalCode: String,
        phoneNumber: String,
        address: String,
        paymentMethod: String,
        onSuccess:(OrderResult)->Unit
    ){
        orderRepository.registerOrder(firstName,lastname,postalCode,phoneNumber,address,paymentMethod)
            .asyncIoNetworkCall()
            .subscribe(object : NikeSingleObserver<OrderResult>(compositeDisposable){
                override fun onSuccess(t: OrderResult) {
                    onSuccess(t)
                }
            })
    }
    init {
        _purchaseDetailOfCart.value = bundle.getParcelable(EXTRA_PURCHASE_DETAIL)

    }
}