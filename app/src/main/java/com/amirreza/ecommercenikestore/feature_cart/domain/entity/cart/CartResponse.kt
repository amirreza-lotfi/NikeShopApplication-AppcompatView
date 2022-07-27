package com.sevenlearn.nikestore.data

import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.ProductInCart

data class CartItem(
    val cart_items: List<ProductInCart>,
    val payable_price: Int,
    val shipping_cost: Int,
    val total_price: Int
)