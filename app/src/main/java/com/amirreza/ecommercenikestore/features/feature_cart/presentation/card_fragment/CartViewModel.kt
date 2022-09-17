package com.amirreza.ecommercenikestore.features.feature_cart.presentation.card_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.EmptyCartState
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.EmptyCartStateFactory
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartItem
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.PurchaseDetail
import com.amirreza.ecommercenikestore.utils.base.NikeSingleObserver
import com.amirreza.ecommercenikestore.utils.base.NikeViewModel
import com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.CartUseCase
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartResponse
import com.amirreza.ecommercenikestore.utils.base.NikeCompletable
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall
import com.amirreza.ecommercenikestore.utils.util.decreaseCartBadgeCount
import com.amirreza.ecommercenikestore.utils.util.hasUserLoggedInAccount
import com.amirreza.ecommercenikestore.utils.util.increaseCartBadgeCount
import io.reactivex.Completable

class CartViewModel(private val cartUseCase: CartUseCase) : NikeViewModel() {
    private val _cartItems = MutableLiveData<MutableList<CartItem>>()
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    private val _purchaseDetailOfCart = MutableLiveData<PurchaseDetail>()
    val purchaseDetailOfCart: LiveData<PurchaseDetail> = _purchaseDetailOfCart

    private val _emptyCartState = MutableLiveData<EmptyCartState>()
    val emptyCartState: LiveData<EmptyCartState> = _emptyCartState

    private val _purchaseDetailAndPurchaseButtonMustShow = MutableLiveData(false)
    val purchaseDetailAndPurchaseButtonMustShow:LiveData<Boolean> = _purchaseDetailAndPurchaseButtonMustShow

    private fun getProductInCart() {
        setPurchaseDetailAndPurchaseButtonMustShow(false)
        if (hasUserLoggedInAccount()) {
            showProgressBar(true)
            cartUseCase.getProducts()
                .asyncIoNetworkCall()
                .doFinally { showProgressBar(false) }
                .subscribe(object : NikeSingleObserver<CartResponse>(compositeDisposable) {
                    override fun onSuccess(t: CartResponse) {
                        if (t.cartItems.isNotEmpty()) {
                            _cartItems.value = t.cartItems.toMutableList()
                            _purchaseDetailOfCart.value = PurchaseDetail(
                                t.totalPrice, t.payablePrice, t.shippingCost
                            )
                            setPurchaseDetailAndPurchaseButtonMustShow(true)
                            _emptyCartState.value = EmptyCartStateFactory.cartIsNotEmpty()
                        } else {
                            _emptyCartState.value = EmptyCartStateFactory.cartIsEmpty()
                        }
                    }
                })
        } else {
            _emptyCartState.value = EmptyCartStateFactory.userHasNotLoggedIn()
        }
    }

    fun refresh() {
        getProductInCart()
    }

    private fun calculatePurchaseDetail() {
        _cartItems.value?.let { cartItems ->
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


    fun uiEvent(event: CartUiEvent) {
        when (event) {
            is CartUiEvent.OnDeleteCartItemClicked -> {
                removeItemFromCart(event.cartItem)
                    .asyncIoNetworkCall()
                    .subscribe(object : NikeCompletable(compositeDisposable) {
                        override fun onComplete() {
                            if(_cartItems.value!!.isEmpty()){
                                _emptyCartState.value = EmptyCartStateFactory.cartIsEmpty()
                            }
                            event.onCompleteEvent()
                        }
                    })
            }
            is CartUiEvent.OnIncreaseItemCountClicked -> {
                increaseCartItemCount(event.cartItem)
                    .asyncIoNetworkCall()
                    .subscribe(object : NikeCompletable(compositeDisposable) {
                        override fun onComplete() {
                            event.onCompleteEvent()
                        }
                    })
            }
            is CartUiEvent.OnDecreaseItemCountClicked -> {
                decreaseCartItemCount(event.cartItem)
                    .asyncIoNetworkCall()
                    .subscribe(object : NikeCompletable(compositeDisposable) {
                        override fun onComplete() {
                            event.onCompleteEvent()
                        }
                    })
            }
        }
    }

    private fun removeItemFromCart(cartItem: CartItem): Completable {
        return cartUseCase
            .remove(cartItem.cartItemId)
            .doOnSuccess {
                calculatePurchaseDetail()
                getProductInCart()
                decreaseCartBadgeCount(cartItem.count)
            }
            .asyncIoNetworkCall()
            .ignoreElement()
    }

    private fun increaseCartItemCount(cartItem: CartItem): Completable {
        return cartUseCase.changeCount(cartItem.cartItemId, cartItem.count + 1)
            .doOnSuccess {
                val indexOfCartItem = _cartItems.value?.indexOf(cartItem) ?: 0
                _cartItems.value?.get(indexOfCartItem)?.count = cartItem.count + 1
                calculatePurchaseDetail()
                increaseCartBadgeCount(1)
                _emptyCartState.postValue(EmptyCartStateFactory.cartIsNotEmpty())
            }
            .ignoreElement()
    }

    private fun decreaseCartItemCount(cartItem: CartItem): Completable {
        return cartUseCase.changeCount(cartItem.cartItemId, cartItem.count - 1)
            .doOnSuccess {
                val indexOfCartItem = _cartItems.value?.indexOf(cartItem) ?: 0
                _cartItems.value?.get(indexOfCartItem)?.count = cartItem.count - 1

                decreaseCartBadgeCount(1)

                calculatePurchaseDetail()
            }
            .ignoreElement()
    }

    private fun setPurchaseDetailAndPurchaseButtonMustShow(mustShow:Boolean){
        _purchaseDetailAndPurchaseButtonMustShow.postValue(mustShow)
    }
}