package com.amirreza.ecommercenikestore.domain.repository

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.facebook.drawee.view.SimpleDraweeView

interface ImageLoaderI {
    fun load(imageView: SimpleDraweeView, url:String)
}