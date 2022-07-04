package com.example.nikeshop.feature_shop.domain.entity

import com.google.gson.annotations.SerializedName

data class Banner(
    val id:Int,

    @SerializedName("image")
    val imageUrl:String,

    @SerializedName("link_type")
    val linkType:Int,

    @SerializedName("link_value")
    val linkValue:String
)
