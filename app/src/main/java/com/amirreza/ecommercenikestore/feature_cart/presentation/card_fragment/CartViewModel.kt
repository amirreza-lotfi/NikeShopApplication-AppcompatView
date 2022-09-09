package com.amirreza.ecommercenikestore.feature_cart.presentation.card_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.feature_auth.domain.model.Tokens
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.EmptyCartState
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.EmptyCartStateFactory
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartItem
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.PurchaseDetail
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeViewModel
import com.amirreza.ecommercenikestore.feature_cart.domain.useCases.CartUseCase
import com.sevenlearn.nikestore.common.asyncIoNetworkCall
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartResponse
import io.reactivex.Completable

class CartViewModel(private val cartUseCase: CartUseCase) : NikeViewModel() {
    private val _cartItem = MutableLiveData<List<CartItem>>()
    val cartResponse: LiveData<List<CartItem>> = _cartItem

    private val _purchaseDetailOfCart = MutableLiveData<PurchaseDetail>()
    val purchaseDetailOfCart: LiveData<PurchaseDetail> = _purchaseDetailOfCart

    private val _emptyCartState = MutableLiveData<EmptyCartState>()
    val emptyCartState: LiveData<EmptyCartState> = _emptyCartState

    private fun getProductInCart() {
        if (hasUserLoggedInAccount()) {
            showProgressBar(true)
            cartUseCase.getProducts()
                .asyncIoNetworkCall()
                .doFinally { showProgressBar(false) }
                .subscribe(object : NikeSingleObserver<CartResponse>(compositeDisposable) {
                    override fun onSuccess(t: CartResponse) {
                        if (t.cartItems.isNotEmpty()) {
                            _cartItem.value = t.cartItems
                            _purchaseDetailOfCart.value = PurchaseDetail(
                                t.totalPrice, t.payablePrice, t.shippingCost
                            )
                        } else {
                            _emptyCartState.value = EmptyCartStateFactory.cartIsEmpty()
                        }
                    }
                })
        } else {
            _emptyCartState.value = EmptyCartStateFactory.userHasNotLoggedIn()
        }
    }

    fun removeItemFromCart(cartItem: CartItem): Completable {
        return cartUseCase
            .remove(cartItem.cartItemId)
            .doOnSuccess {
                calculatePurchaseDetail()
                _cartItem.value?.let {
                    if (it.isEmpty()) {
                        _emptyCartState.postValue(EmptyCartStateFactory.cartIsEmpty())
                    }
                }
            }
            .asyncIoNetworkCall()
            .ignoreElement()
    }

    fun increaseCartItemCount(cartItem: CartItem): Completable {
        return cartUseCase.changeCount(cartItem.cartItemId, cartItem.count + 1)
            .doOnSuccess {
                val indexOfCartItem = _cartItem.value?.indexOf(cartItem) ?: 0
                _cartItem.value?.get(indexOfCartItem)?.count = cartItem.count +1
                calculatePurchaseDetail()
                _emptyCartState.postValue(EmptyCartStateFactory.cartIsNotEmpty())
            }
            .ignoreElement()
    }

    fun decreaseCartItemCount(cartItem: CartItem): Completable {
        return cartUseCase.changeCount(cartItem.cartItemId, cartItem.count - 1)
            .doOnSuccess {
                val indexOfCartItem = _cartItem.value?.indexOf(cartItem) ?: 0
                _cartItem.value?.get(indexOfCartItem)?.count = cartItem.count -1
                calculatePurchaseDetail()
            }
            .ignoreElement()
    }

    fun refresh() {
        getProductInCart()
    }

    private fun calculatePurchaseDetail() {
        _cartItem.value?.let { cartItems ->
            _purchaseDetailOfCart.value?.let { purchase ->
                val totalPrice = cartItems.sumOf { cart ->
                    cart.count * cart.product.getRealPrice()
                }
                val payAblePrice = cartItems.sumOf { cart ->
                    cart.count * cart.product.price
                }
                val deliveryCost = if (payAblePrice > 500000) 0 else payAblePrice / 10

                purchase.deliveryCost = deliveryCost
                purchase.totalPrice = totalPrice
                purchase.payAblePrice = payAblePrice

                _purchaseDetailOfCart.postValue(purchase)
            }
        }
    }

    private fun hasUserLoggedInAccount(): Boolean {
        return !Tokens.token.isNullOrBlank()
    }
}