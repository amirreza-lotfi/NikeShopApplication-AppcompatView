package com.amirreza.ecommercenikestore.domain.useCases

import com.amirreza.ecommercenikestore.domain.useCases.cart_usecases.*

data class CartUseCase(
    val addToCart: AddToCartUC,
    val getProducts: GetProductsInShoppingListUC,
    val remove: RemoveProductFromShoppingCartUC,
    val changeCount: ChangeCountUC,
    val getItems: GetItemsInTheShoppingCart,
)