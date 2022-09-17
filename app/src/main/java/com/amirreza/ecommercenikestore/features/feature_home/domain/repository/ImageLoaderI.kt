package com.amirreza.ecommercenikestore.features.feature_home.domain.repository

import com.facebook.drawee.view.SimpleDraweeView

interface ImageLoaderI {
    fun load(imageView: SimpleDraweeView, url:String)
}