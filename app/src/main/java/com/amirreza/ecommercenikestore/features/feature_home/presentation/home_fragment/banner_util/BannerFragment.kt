package com.amirreza.ecommercenikestore.features.feature_home.presentation.home_fragment.banner_util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.utils.util.EXTRA_KEY_DATA
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.ImageLoaderI
import com.example.nikeshop.feature_shop.domain.entity.Banner
import com.facebook.drawee.view.SimpleDraweeView
import org.koin.android.ext.android.inject
import java.lang.IllegalStateException

class BannerFragment:Fragment() {
    private val imageLoader:ImageLoaderI by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageView = inflater.inflate(R.layout.fragment_banner, container, false) as SimpleDraweeView

        val banner =requireArguments().getParcelable<Banner>(EXTRA_KEY_DATA) ?: throw IllegalStateException()
        imageLoader.load(imageView,banner.imageUrl)
        return imageView
    }


    companion object{
        fun newInstance(banner: Banner): BannerFragment {
            return BannerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_KEY_DATA, banner)
                }
            }
        }
    }
}