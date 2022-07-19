package com.amirreza.ecommercenikestore.feature_store.data.repository
import com.facebook.drawee.view.SimpleDraweeView
import com.amirreza.ecommercenikestore.feature_store.domain.repository.ImageLoaderI

class FrescoImageLoadingService:ImageLoaderI {
    override fun load(imageView: SimpleDraweeView, url: String) {
        imageView.setImageURI(url)
    }
}