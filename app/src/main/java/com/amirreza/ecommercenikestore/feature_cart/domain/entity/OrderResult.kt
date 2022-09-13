package com.amirreza.ecommercenikestore.feature_cart.domain.entity

import com.google.gson.annotations.SerializedName

data class OrderResult(

    @SerializedName("order_id")
    val orderId: Int,

    @SerializedName("bank_gateway_url")
    val bankGatewayUrl: String
)