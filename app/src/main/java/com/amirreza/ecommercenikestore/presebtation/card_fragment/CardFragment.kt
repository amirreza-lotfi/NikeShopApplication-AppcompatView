package com.amirreza.ecommercenikestore.presebtation.card_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.common.base.NikeFragment

class CardFragment:NikeFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card,container,false)
    }
}