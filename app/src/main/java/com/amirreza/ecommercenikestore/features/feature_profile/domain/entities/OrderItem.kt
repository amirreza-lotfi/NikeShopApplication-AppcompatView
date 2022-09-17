package com.amirreza.ecommercenikestore.features.feature_profile.domain.entities

import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product

data class OrderItem(
    val count: Int,
    val discount: Int,
    val id: Int,
    val order_id: Int,
    val price: Int,
    val product: Product,
    val product_id: Int
)