package com.amirreza.ecommercenikestore.features.feature_cart.presentation.receipt_fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.amirreza.ecommercenikestore.databinding.ActivityOrderResultBinding
import com.amirreza.ecommercenikestore.utils.base.NikeActivity
import com.amirreza.ecommercenikestore.utils.util.EXTRA_KEY_ORDER_ID
import com.amirreza.ecommercenikestore.utils.util.formatPrice
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OrderResultActivity : NikeActivity() {
    private lateinit var binding:ActivityOrderResultBinding
    private val viewModel by viewModel<OrderResultViewModel> {
        val uri: Uri? = intent.data
        if (uri != null)
            parametersOf(uri.getQueryParameter("order_id")!!.toInt())
        else
            parametersOf(intent.extras!!.getInt(EXTRA_KEY_ORDER_ID))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.orderDetail.observe(this){
            binding.priceTv.text = formatPrice(it.payablePrice," تومان ")
            binding.purchaseStatusTv.text = it.paymentStatus
            binding.DetailPayingTitleTv.text = if(it.purchaseSuccess) "خرید با موفقیت انجام شد" else "خرید ناموفق"
        }
        binding.orderHistoryBtn.setOnClickListener {
            ///todo implement orderHistory
        }
        binding.toolbar.onBackButtonClickListener = onClickHomeOrBack
        binding.returnToHomeBtn.setOnClickListener(onClickHomeOrBack)

    }
    private val onClickHomeOrBack = View.OnClickListener {
        finish()
    }
}