package com.amirreza.ecommercenikestore.feature_cart.presentation.receipt_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.OrderDetail
import com.amirreza.ecommercenikestore.feature_cart.domain.repository.OrderRepository
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeViewModel
import com.amirreza.ecommercenikestore.feature_store.common.util.asyncIoNetworkCall

class OrderResultViewModel(
    private val orderRepository: OrderRepository,
    private val orderId:Int
):NikeViewModel() {
    private val _orderDetail = MutableLiveData<OrderDetail>()
    val orderDetail: LiveData<OrderDetail> = _orderDetail

    init {
        orderRepository.getOrderDetail(orderId)
            .asyncIoNetworkCall()
            .subscribe(object : NikeSingleObserver<OrderDetail>(compositeDisposable) {
                override fun onSuccess(t: OrderDetail) {
                    _orderDetail.value = t
                }
            })
    }
}