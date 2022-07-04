package com.example.nikeshop.feature_shop.domain.entity



@Parcelize
data class Product(
    val discount: Int,
    val id: Int,
    val image: String,
    val previous_price: Int,
    val price: Int,
    val status: Int,
    val title: String
): Parcelable {

    companion object : Parcelable {
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
