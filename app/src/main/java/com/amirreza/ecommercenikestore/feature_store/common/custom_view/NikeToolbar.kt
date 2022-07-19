package com.amirreza.ecommercenikestore.feature_store.common.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.amirreza.ecommercenikestore.R
import kotlinx.android.synthetic.main.view_toolbar.view.*

class NikeToolbar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    var onBackButtonClickListener: View.OnClickListener?=null
        set(value) {
            field = value
            backButton.setOnClickListener(onBackButtonClickListener)
        }
    init {
        inflate(context, R.layout.view_toolbar, this)
        if (attrs != null) {
            val attrs = context.obtainStyledAttributes(attrs,R.styleable.NikeToolbar)
            val toolbarText = attrs.getString(R.styleable.NikeToolbar_NikeToolbarTitle)
            if(toolbarText!=null){
                toolbarTitleTextView.text = toolbarText
            }
            attrs.recycle()
        }
    }
}