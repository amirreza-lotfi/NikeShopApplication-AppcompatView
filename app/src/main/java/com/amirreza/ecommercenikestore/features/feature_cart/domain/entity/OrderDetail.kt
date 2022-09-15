package com.amirreza.ecommercenikestore.features.feature_cart.domain.entity

import com.google.gson.annotations.SerializedName


data class OrderDetail(
    @SerializedName("purchase_success")
    val purchaseSuccess: Boolean,

    @SerializedName("payable_price")
    val payablePrice: Int,

    @SerializedName("payment_status")
    val paymentStatus: String
)