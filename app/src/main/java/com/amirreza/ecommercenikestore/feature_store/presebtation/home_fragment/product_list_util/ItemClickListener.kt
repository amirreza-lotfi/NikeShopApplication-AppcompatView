package com.amirreza.ecommercenikestore.feature_store.presebtation.home_fragment.product_list_util

import com.example.nikeshop.feature_shop.domain.entity.Product

interface ItemClickListener {
    fun onClick(product: Product)
}