package com.amirreza.ecommercenikestore.features.feature_profile.data

import com.amirreza.ecommercenikestore.features.feature_profile.domain.entities.OrderHistoryItem
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.OrderHistoryRepository
import com.amirreza.ecommercenikestore.http.ApiService
import io.reactivex.Single

class OrderHistoryRepositoryImpl(private val apiService: ApiService): OrderHistoryRepository {
    override fun getOrders(): Single<List<OrderHistoryItem>> {
        return apiService.orders()
    }
}