package com.amirreza.ecommercenikestore.features.feature_store.presentation.home_fragment.product_list_util

import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product

interface ItemClickListener {
    fun onClick(product: Product)
}