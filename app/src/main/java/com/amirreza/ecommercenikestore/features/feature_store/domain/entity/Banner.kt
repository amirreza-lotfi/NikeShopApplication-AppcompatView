package com.example.nikeshop.feature_shop.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Banner(
    val id:Int,

    @SerializedName("image")
    val imageUrl:String,

    @SerializedName("link_type")
    val linkType:Int,

    @SerializedName("link_value")
    val linkValue:String
):Parcelable
