package com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart

import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import com.google.gson.annotations.SerializedName

data class CartItem(

    @SerializedName("cart_item_id")
    val cartItemId: Int,
    var count: Int,
    val product: Product,
    var progressBarVisibility:Boolean
)