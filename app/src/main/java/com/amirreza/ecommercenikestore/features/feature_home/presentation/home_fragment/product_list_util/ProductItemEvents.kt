package com.amirreza.ecommercenikestore.features.feature_home.presentation.home_fragment.product_list_util

import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product

interface ProductItemEvents {
    fun onClick(product: Product)
    fun onFavoriteButtonClick(product: Product)
}