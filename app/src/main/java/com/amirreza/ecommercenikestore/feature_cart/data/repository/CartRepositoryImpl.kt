package com.amirreza.ecommercenikestore.feature_cart.data.repository

import com.amirreza.ecommercenikestore.feature_cart.data.data_source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.MessageResponse
import com.amirreza.ecommercenikestore.feature_cart.domain.repository.CartShoppingRepository
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartResponse
import io.reactivex.Single

class CartRepositoryImpl(private val cartDataSourceI: CartDataSourceI):CartShoppingRepository {
    override fun addToCart(productId: Int): Single<AddToCartResponse> {
        return cartDataSourceI.addToCart(productId)
    }

    override fun getProductsInShoppingCart(): Single<CartResponse> {
        return cartDataSourceI.getProductsInShoppingCart()
    }

    override fun remove(cartItemId: Int): Single<MessageResponse> {
        return cartDataSourceI.remove(cartItemId)
    }

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse> {
        return cartDataSourceI.changeCount(cartItemId,count)
    }

    override fun getItemsInTheShoppingCart(): Single<ProductCountInShoppingCart> {
        return cartDataSourceI.getCountOfProductsInShoppingCart()
    }

}