package com.amirreza.ecommercenikestore.feature_cart.data.repository

import com.amirreza.ecommercenikestore.feature_cart.domain.entity.OrderDetail
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.OrderResult
import com.amirreza.ecommercenikestore.feature_cart.domain.repository.OrderRepository
import com.amirreza.ecommercenikestore.http.ApiService
import io.reactivex.Single

class OrderRepositoryImpl(val apiService: ApiService):OrderRepository {
    override fun registerOrder(): Single<OrderResult> {
        return apiService.registerOrder()
    }

    override fun getOrderDetail(orderId: Int): Single<OrderDetail> {
        return apiService.getOrderDetail(orderId)
    }
}