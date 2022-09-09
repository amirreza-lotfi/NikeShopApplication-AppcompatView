package com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart

import android.widget.ProgressBar
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.google.gson.annotations.SerializedName

data class CartItem(

    @SerializedName("cart_item_id")
    val cartItemId: Int,
    var count: Int,
    val product: Product,
    var progressBarVisibility:Boolean
)