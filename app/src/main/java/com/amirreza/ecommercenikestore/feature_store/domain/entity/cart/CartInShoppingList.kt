package com.sevenlearn.nikestore.data

import com.amirreza.ecommercenikestore.feature_store.domain.entity.cart.CartItem

data class CartInShoppingList(
    val cart_items: List<CartItem>,
    val payable_price: Int,
    val shipping_cost: Int,
    val total_price: Int
)