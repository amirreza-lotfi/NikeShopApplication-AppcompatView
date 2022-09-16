package com.amirreza.ecommercenikestore.features.feature_profile.presentation.order_history_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.ItemOrderHistoryBinding
import com.amirreza.ecommercenikestore.features.feature_profile.domain.entities.OrderHistoryItem
import com.amirreza.ecommercenikestore.features.feature_store.common.util.convertDpToPixel
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.ImageLoaderI
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.item_order_history.view.*

class OrderHistoryAdapter(
    private val orders:List<OrderHistoryItem>,
    private val context: Context,
):RecyclerView.Adapter<OrderHistoryAdapter.ItemHolder>() {
    private val layoutParams: LinearLayout.LayoutParams

    init {
        val size = convertDpToPixel(100f, context).toInt()
        val margin = convertDpToPixel(8f, context).toInt()
        layoutParams = LinearLayout.LayoutParams(size, size)
        layoutParams.setMargins(margin, 0, margin, 0)
    }

    inner class ItemHolder(val binding:ItemOrderHistoryBinding):ViewHolder(binding.root){
        fun bind(orderHistoryItem:OrderHistoryItem){
            binding.orderIdTv.text = orderHistoryItem.id.toString()
            binding.orderAmountTv.text = orderHistoryItem.payable.toString()
            binding.orderProductsLl.removeAllViews()

            orderHistoryItem.orderItems.forEach {
                val imageView = SimpleDraweeView(context)
                imageView.layoutParams = layoutParams
                imageView.setImageURI(it.product.image)
                binding.orderProductsLl.addView(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemHolder(
            ItemOrderHistoryBinding.inflate(
                layoutInflater
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}