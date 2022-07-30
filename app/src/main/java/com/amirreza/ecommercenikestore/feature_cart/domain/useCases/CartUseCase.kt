package com.amirreza.ecommercenikestore.feature_cart.domain.useCases

import com.amirreza.ecommercenikestore.feature_cart.domain.useCases.cart_usecases.*

data class CartUseCase(
    val addToCart: AddToCartUC,
    val getProducts: GetProductsInShoppingListUC,
    val remove: RemoveProductFromShoppingCartUC,
    val changeCount: ChangeCountUC,
    val getItemsCount: GetItemsInTheShoppingCart,
)