package com.amirreza.ecommercenikestore.features.feature_cart.presentation.checkout_fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.fragment.findNavController
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentCheckoutBinding
import com.amirreza.ecommercenikestore.features.feature_cart.presentation.receipt_fragment.OrderResultActivity
import com.amirreza.ecommercenikestore.features.feature_store.common.util.EXTRA_KEY_ORDER_ID
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.lang.Exception

const val METHOD_CASH_ON_DELIVERY = "cash_on_delivery"
const val METHOD_ONLINE_PAYMENT = "online"

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

        binding.codBtn.setOnClickListener(clickOnPurchaseButton)
        binding.onlinePaymentBtn.setOnClickListener(clickOnPurchaseButton)



    }

    private val clickOnPurchaseButton = View.OnClickListener { button->
        viewModel.registerOrder(
            binding.shippingListFirstNameEt.text.toString(),
            binding.shippingListLastNameEt.text.toString(),
            binding.shippingZipCodeNameEt.text.toString(),
            binding.shippingListMobileNumberEt.text.toString(),
            binding.shippingListAddressEt.text.toString(),
            if (button.id == R.id.codBtn) METHOD_CASH_ON_DELIVERY else METHOD_ONLINE_PAYMENT,
            onSuccess = { orderResult->

                if(orderResult.bankGatewayUrl.isEmpty()){
                    requireActivity().startActivity(
                        Intent(
                            requireContext(),
                            OrderResultActivity::class.java
                        ).apply {
                            this.putExtra(EXTRA_KEY_ORDER_ID,orderResult.orderId)
                        }
                    )
                }else{
                    openUrlInBrowser(orderResult.bankGatewayUrl)
                }
                findNavController().navigate(R.id.action_checkoutFragment_to_navigation_home)
            }
        )
    }

    private fun openUrlInBrowser(url:String){
        try{
            val fadeIn = android.R.anim.fade_in
            val fadeOut = android.R.anim.fade_out

            val uri = Uri.parse(url)
            val customTabsIntent = CustomTabsIntent.Builder()
                .setStartAnimations(requireContext(),fadeIn,fadeOut)
                .setExitAnimations(requireContext(),fadeIn,fadeOut)
                .build()
            customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            customTabsIntent.launchUrl(requireContext(),uri)
        }catch (e:Exception){}
    }

}