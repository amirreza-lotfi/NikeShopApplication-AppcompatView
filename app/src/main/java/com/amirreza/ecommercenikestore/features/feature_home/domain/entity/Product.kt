package com.amirreza.ecommercenikestore.features.feature_home.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite")
@Parcelize
data class Product(
    val discount: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    var previous_price: Int,
    val price: Int,
    val status: Int,
    val title: String
): Parcelable {
    var isFavorite = false
    fun getRealPrice():Int{
        return price+discount
    }
    fun getPayablePrice():Int{
        return price
    }
}

const val SORT_NEWEST = 0
const val SORT_POPULAR = 1
const val SORT_PRICE_DEC = 2
const val SORT_PRICE_ASC = 3
