package com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PurchaseDetail (
    var totalPrice:Int,
    var payAblePrice:Int,
    var deliveryCost:Int
):Parcelable