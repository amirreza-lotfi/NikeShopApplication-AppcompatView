package com.amirreza.ecommercenikestore.data.repository
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import androidx.appcompat.widget.AppCompatImageView
import com.amirreza.ecommercenikestore.domain.repository.ImageLoaderI

class FrescoImageLoadingService:ImageLoaderI {
    override fun load(imageView: SimpleDraweeView, url: String) {
        imageView.setImageURI(url)
    }
}