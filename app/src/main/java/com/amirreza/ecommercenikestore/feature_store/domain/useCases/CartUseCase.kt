package com.amirreza.ecommercenikestore.feature_store.domain.useCases

import com.amirreza.ecommercenikestore.feature_store.domain.useCases.cart_usecases.*

data class CartUseCase(
    val addToCart: AddToCartUC,
    val getProducts: GetProductsInShoppingListUC,
    val remove: RemoveProductFromShoppingCartUC,
    val changeCount: ChangeCountUC,
    val getItems: GetItemsInTheShoppingCart,
)