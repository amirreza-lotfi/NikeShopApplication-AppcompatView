package com.amirreza.ecommercenikestore.features.feature_profile.data

import com.amirreza.ecommercenikestore.utils.database.FavoriteDao
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.FavoriteRepository
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao): FavoriteRepository {
    override fun getFavoriteProducts(): Single<List<Product>> {
        return favoriteDao.getFavoriteProducts()
    }

    override fun addProductToFavorites(product: Product): Completable {
        return favoriteDao.addToFavorite(product)
    }

    override fun deleteProductToFavorites(product: Product): Completable {
        return favoriteDao.deleteFromFavorites(product)
    }
}