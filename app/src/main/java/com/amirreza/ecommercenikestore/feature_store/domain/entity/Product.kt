package com.example.nikeshop.feature_shop.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Product(
    val discount: Int,
    val id: Int,
    val image: String,
    var previous_price: Int,
    val price: Int,
    val status: Int,
    val title: String
): Parcelable {
    fun getRealPrice():Int{
        return price+discount
    }
    fun getPayablePrice():Int{
        return price
    }
    companion object {
        fun getPriceWithSeparator(price:String): String {
            var counter = 0
            val priceToString = price
            var price = ""
            var index = priceToString.length - 1

            while (index >= 0) {
                if (counter == 3) {
                    price += ","
                    price += priceToString[index]
                    counter = 1
                } else {
                    price += priceToString[index]
                    counter++
                }
                index--
            }
            return price.reversed()
        }
    }
}

const val SORT_NEWEST = 0
const val SORT_POPULAR = 1
const val SORT_PRICE_DEC = 2
const val SORT_PRICE_ASC = 3
