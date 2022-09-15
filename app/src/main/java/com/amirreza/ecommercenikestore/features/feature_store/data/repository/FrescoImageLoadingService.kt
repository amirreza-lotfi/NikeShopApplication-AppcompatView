package com.amirreza.ecommercenikestore.features.feature_store.data.repository
import com.facebook.drawee.view.SimpleDraweeView
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.ImageLoaderI

class FrescoImageLoadingService:ImageLoaderI {
    override fun load(imageView: SimpleDraweeView, url: String) {
        imageView.setImageURI(url)
    }
}