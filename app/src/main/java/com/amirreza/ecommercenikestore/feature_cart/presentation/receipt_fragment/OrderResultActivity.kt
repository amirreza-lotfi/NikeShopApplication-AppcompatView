package com.amirreza.ecommercenikestore.feature_cart.presentation.receipt_fragment

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.ActivityOrderResultBinding
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeActivity
import com.amirreza.ecommercenikestore.feature_store.common.util.EXTRA_KEY_ORDER_ID
import org.koin.android.ext.android.inject
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
            binding.priceTv.text = it.payablePrice.toString()
            binding.purchaseStatusTv.text = it.paymentStatus
            binding.DetailPayingTitleTv.text = if(it.purchaseSuccess) "خرید با موفقیت انجام شد" else "خرید ناموفق"
        }
    }
}