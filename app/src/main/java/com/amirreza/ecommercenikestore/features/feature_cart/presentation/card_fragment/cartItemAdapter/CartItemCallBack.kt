package com.amirreza.ecommercenikestore.features.feature_cart.presentation.card_fragment.cartItemAdapter

import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartItem

interface CartItemCallBack {
    fun onDeleteCartItemClicked(cartItem: CartItem)
    fun onIncreaseItemCountClicked(cartItem: CartItem)
    fun onDecreaseItemCountClicked(cartItem: CartItem)
    fun onProductImageClicked(cartItem: CartItem)
}