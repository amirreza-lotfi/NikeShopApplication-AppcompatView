package com.amirreza.ecommercenikestore.feature_store.data.source.cart_data_source;

import com.amirreza.ecommercenikestore.http.ApiService;
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.AddToCartResponse
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartItemCount
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.MessageResponse
import com.google.gson.JsonObject
import com.sevenlearn.nikestore.data.CartInShoppingList
import io.reactivex.Single

class CartRemoteDataSource(private val apiService:ApiService):CartDataSourceI{
    override fun addToCart(productId: Int): Single<AddToCartResponse> {
        return apiService.addToCart(
            JsonObject().apply {
                addProperty("product_id",productId)
            }
        )
    }

    override fun getProductsInShoppingCart(): Single<CartInShoppingList> {
        TODO("Not yet implemented")
    }

    override fun remove(cartItemId: Int): Single<MessageResponse> {
        TODO("Not yet implemented")
    }

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse> {
        TODO("Not yet implemented")
    }

    override fun getItemsInTheShoppingCart(): Single<CartItemCount> {
        TODO("Not yet implemented")
    }
}
