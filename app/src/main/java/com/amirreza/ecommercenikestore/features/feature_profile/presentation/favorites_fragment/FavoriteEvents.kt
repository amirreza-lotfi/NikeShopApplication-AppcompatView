package com.amirreza.ecommercenikestore.features.feature_profile.presentation.favorites_fragment

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product

interface FavoriteEvents {
    fun onLongClick(product: Product, size:Int)
    fun onClick(product: Product)
}