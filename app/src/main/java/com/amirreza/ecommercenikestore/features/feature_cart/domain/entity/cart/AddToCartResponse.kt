package com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart

import com.google.gson.annotations.SerializedName

/**
 * we send post request that adds product to shopping list. this function
 * return response. the response is AddToCartResponse
 */
data class AddToCartResponse(
    val count: Int,

    @SerializedName("id")
    val productInShoppingCartId: Int,

    @SerializedName("product_id")
    val productId: Int
)