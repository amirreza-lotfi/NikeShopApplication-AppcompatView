package com.amirreza.ecommercenikestore.feature_cart.data.repository

import com.amirreza.ecommercenikestore.feature_cart.domain.entity.OrderDetail
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.OrderResult
import com.amirreza.ecommercenikestore.feature_cart.domain.repository.OrderRepository
import com.amirreza.ecommercenikestore.http.ApiService
import com.google.gson.JsonObject
import io.reactivex.Single

class OrderRepositoryImpl(private val apiService: ApiService):OrderRepository {
    override fun registerOrder(
        firstName: String,
        lastname: String,
        postalCode: String,
        phoneNumber: String,
        address: String,
        paymentMethod: String
    ): Single<OrderResult> {
        return apiService.registerOrder(JsonObject().apply {
            addProperty("first_name", firstName)
            addProperty("last_name", lastname)
            addProperty("postal_code", postalCode)
            addProperty("mobile", phoneNumber)
            addProperty("address", address)
            addProperty("payment_method", paymentMethod)
        })
    }

    override fun getOrderDetail(orderId: Int): Single<OrderDetail> {
        return apiService.getOrderDetail(orderId)
    }
}