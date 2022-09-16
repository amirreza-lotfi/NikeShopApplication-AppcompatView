package com.amirreza.ecommercenikestore.features.feature_profile.domain.entities

import com.google.gson.annotations.SerializedName

data class OrderHistoryItem(
    val id: Int,
    val payable: Int,
    @SerializedName("order_items")
    val orderItems: List<OrderItem>,
)