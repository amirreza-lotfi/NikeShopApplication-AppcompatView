package com.amirreza.ecommercenikestore.presebtation.home_fragment.product_list_util

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.ItemProductBinding
import com.amirreza.ecommercenikestore.domain.repository.ImageLoaderI
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.facebook.drawee.view.SimpleDraweeView
import com.sevenlearn.nikestore.common.formatPrice

class ProductListAdapter(val imageLoaderI: ImageLoaderI): RecyclerView.Adapter<ProductListAdapter.ItemHolder>() {
    var products: List<Product> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBind(products[position])
    }

    inner class  ItemHolder(item: View):RecyclerView.ViewHolder(item){
        private val productIv: SimpleDraweeView = item.findViewById(R.id.item_product_image)
        private val titleTv: AppCompatTextView = item.findViewById(R.id.productTitleTv)
        private val currentPriceTv: AppCompatTextView = item.findViewById(R.id.currentPriceTv)
        private val previousPriceTv: AppCompatTextView = item.findViewById(R.id.previousPriceTv)

        fun onBind(product: Product){
            imageLoaderI.load(productIv,product.image)
            titleTv.text = product.title
            currentPriceTv.text = formatPrice(product.price)
            previousPriceTv.text = formatPrice(product.previous_price)
            previousPriceTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}