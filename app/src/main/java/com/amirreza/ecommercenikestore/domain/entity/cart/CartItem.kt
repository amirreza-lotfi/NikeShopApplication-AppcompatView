package com.amirreza.ecommercenikestore.domain.entity.cart

import com.example.nikeshop.feature_shop.domain.entity.Product
import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("cart_item_id")
    val cartItemId: Int,
    val count: Int,
    val product: Product
)