package com.amirreza.ecommercenikestore.utils.database

import androidx.room.*
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorite(product: Product): Completable

    @Delete
    fun deleteFromFavorites(product: Product): Completable

    @Query("SELECT * FROM favorite")
    fun getFavoriteProducts(): Single<List<Product>>
}