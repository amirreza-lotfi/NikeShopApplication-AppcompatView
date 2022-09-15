package com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("cart_items")
    val cartItems: List<CartItem>,

    @SerializedName("payable_price")
    val payablePrice: Int,

    @SerializedName("shipping_cost")
    val shippingCost: Int,

    @SerializedName("total_price")
    val totalPrice: Int
)