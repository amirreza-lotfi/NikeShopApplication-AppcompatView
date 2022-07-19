package com.amirreza.ecommercenikestore.feature_store.domain.useCases

import com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases.AddProductToFavoritesUC
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases.DeleteProductFromFavoritesUC
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases.GetFavoriteProductsUC
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases.GetProductsUC

data class ProductUseCases(
    val getProductsUC: GetProductsUC,
    val getFavoriteProductsUC: GetFavoriteProductsUC,
    val addProductToFavoriteUC: AddProductToFavoritesUC,
    val deleteProductFromFavoritesUC: DeleteProductFromFavoritesUC,
)
