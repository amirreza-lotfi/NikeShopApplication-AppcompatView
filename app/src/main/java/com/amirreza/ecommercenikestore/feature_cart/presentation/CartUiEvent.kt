package com.amirreza.ecommercenikestore.feature_cart.presentation

import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartItem

sealed class CartUiEvent{
    data class OnDeleteCartItemClicked(val cartItem: CartItem, val onCompleteEvent:()->Unit):CartUiEvent()
    data class OnIncreaseItemCountClicked(val cartItem: CartItem, val onCompleteEvent: () -> Unit):CartUiEvent()
    data class OnDecreaseItemCountClicked(val cartItem: CartItem, val onCompleteEvent: () -> Unit):CartUiEvent()
}
