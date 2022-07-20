package com.amirreza.ecommercenikestore.feature_store.domain.useCases.cart_usecases

import com.amirreza.ecommercenikestore.feature_store.data.source.cart_data_source.CartDataSourceI
import com.sevenlearn.nikestore.data.CartInShoppingList
import io.reactivex.Single

class GetProductsInShoppingListUC (
    private val repo: CartDataSourceI
) {
    operator fun invoke(): Single<CartInShoppingList> {
        return repo.getProductsInShoppingCart()
    }
}