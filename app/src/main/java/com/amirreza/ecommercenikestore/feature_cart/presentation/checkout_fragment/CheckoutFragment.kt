package com.amirreza.ecommercenikestore.feature_cart.presentation.checkout_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentCheckoutBinding
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_ALL_COMMENTS
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class CheckoutFragment : Fragment() {
    private lateinit var binding:FragmentCheckoutBinding
    private val viewModel:CheckoutViewModel by inject {
        parametersOf(this.arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.purchaseDetailOfCart.observe(viewLifecycleOwner){
            binding.totalPriceTV.text = "${it.totalPrice} تومان "
            binding.shippingCostTV.text = "${it.deliveryCost} تومان "
            binding.payablePriceTV.text = "${it.payAblePrice} تومان "
        }
    }

}