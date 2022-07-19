package com.amirreza.ecommercenikestore.feature_store.domain.repository

import com.facebook.drawee.view.SimpleDraweeView

interface ImageLoaderI {
    fun load(imageView: SimpleDraweeView, url:String)
}