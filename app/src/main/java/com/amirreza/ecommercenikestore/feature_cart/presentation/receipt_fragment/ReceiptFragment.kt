package com.amirreza.ecommercenikestore.feature_cart.presentation.receipt_fragment

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentReceiptBinding


class ReceiptFragment : Fragment() {
    private lateinit var binding:FragmentReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentReceiptBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}