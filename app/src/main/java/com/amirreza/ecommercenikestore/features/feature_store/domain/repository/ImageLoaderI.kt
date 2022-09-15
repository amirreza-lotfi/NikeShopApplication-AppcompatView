package com.amirreza.ecommercenikestore.features.feature_store.domain.repository

import com.facebook.drawee.view.SimpleDraweeView

interface ImageLoaderI {
    fun load(imageView: SimpleDraweeView, url:String)
}