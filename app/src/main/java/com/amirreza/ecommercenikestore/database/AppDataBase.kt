package com.amirreza.ecommercenikestore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun favoriteDao():FavoriteDao
}