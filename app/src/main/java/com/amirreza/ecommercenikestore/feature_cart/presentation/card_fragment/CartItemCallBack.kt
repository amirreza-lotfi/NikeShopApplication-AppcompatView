package com.amirreza.ecommercenikestore.feature_cart.presentation.card_fragment

import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartItem

interface CartItemCallBack {
    fun onDeleteCartItemClicked(cartItem: CartItem)
    fun onIncreaseItemCountClicked(cartItem: CartItem)
    fun onDecreaseItemCountClicked(cartItem: CartItem)
    fun onProductImageClicked(cartItem: CartItem)
}