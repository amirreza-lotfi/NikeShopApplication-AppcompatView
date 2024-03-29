package com.amirreza.ecommercenikestore.features.feature_cart.domain.repository

import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.OrderDetail
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.OrderResult
import io.reactivex.Single

interface OrderRepository {
    fun registerOrder(
        firstName: String,
        lastname: String,
        postalCode: String,
        phoneNumber: String,
        address: String,
        paymentMethod: String
    ): Single<OrderResult>

    fun getOrderDetail(orderId: Int): Single<OrderDetail>
}