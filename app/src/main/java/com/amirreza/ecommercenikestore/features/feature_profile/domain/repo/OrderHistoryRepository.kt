package com.amirreza.ecommercenikestore.features.feature_profile.domain.repo

import com.amirreza.ecommercenikestore.features.feature_profile.domain.entities.OrderHistoryItem
import io.reactivex.Single

interface OrderHistoryRepository {
    fun getOrders(): Single<List<OrderHistoryItem>>
}