package com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PurchaseDetail (
    var totalPrice:Int,
    var payAblePrice:Int,
    var deliveryCost:Int
):Parcelable