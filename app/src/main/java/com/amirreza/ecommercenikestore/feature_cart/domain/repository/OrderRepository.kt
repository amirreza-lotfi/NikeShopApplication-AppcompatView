package com.amirreza.ecommercenikestore.feature_cart.domain.repository

import com.amirreza.ecommercenikestore.feature_cart.domain.entity.OrderDetail
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.OrderResult
import io.reactivex.Single

interface OrderRepository {
    fun registerOrder():Single<OrderResult>
    fun getOrderDetail(orderId:Int):Single<OrderDetail>
}