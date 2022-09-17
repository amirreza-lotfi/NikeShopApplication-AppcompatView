package com.amirreza.ecommercenikestore.features.feature_profile.presentation.order_history_fragment

import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.features.feature_profile.domain.entities.OrderHistoryItem
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.OrderHistoryRepository
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall

class OrderHistoryViewModel(
    private val orderHistoryRepository: OrderHistoryRepository
) : NikeViewModel() {
    val orders = MutableLiveData<List<OrderHistoryItem>>()

    init {
        progressBarIndicatorLiveData.value = true
        orderHistoryRepository.getOrders()
            .asyncIoNetworkCall()
            .doFinally {
                progressBarIndicatorLiveData.value = false
            }
            .subscribe(object : NikeSingleObserver<List<OrderHistoryItem>>(compositeDisposable){
                override fun onSuccess(t: List<OrderHistoryItem>) {
                    orders.postValue(t)
                }
            })
    }
}