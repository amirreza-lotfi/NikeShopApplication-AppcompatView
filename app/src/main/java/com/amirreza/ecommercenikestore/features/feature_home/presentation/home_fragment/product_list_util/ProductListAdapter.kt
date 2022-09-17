package com.amirreza.ecommercenikestore.features.feature_home.presentation.home_fragment.product_list_util

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.ImageLoaderI
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import com.facebook.drawee.view.SimpleDraweeView
import com.amirreza.ecommercenikestore.utils.util.formatPrice
import com.amirreza.ecommercenikestore.utils.util.implementSpringAnimationTrait

class ProductListAdapter(val imageLoaderI: ImageLoaderI): RecyclerView.Adapter<ProductListAdapter.ItemHolder>() {
    var itemClickListener: ProductItemEvents? = null
        set(value) {
            field = value
        }

    var products: ArrayList<Product> = arrayListOf()
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

    inner class  ItemHolder(val item: View):RecyclerView.ViewHolder(item){
        private val productIv: SimpleDraweeView = item.findViewById(R.id.item_product_image)
        private val titleTv: TextView = item.findViewById(R.id.productTitleTv)
        private val currentPriceTv: TextView = item.findViewById(R.id.currentPriceTv)
        private val previousPriceTv: TextView = item.findViewById(R.id.previousPriceTv)
        private val favoriteBtn:ImageView = item.findViewById(R.id.favoriteBtn)

        fun onBind(product: Product){
            imageLoaderI.load(productIv,product.image)
            titleTv.text = product.title
            currentPriceTv.text = formatPrice(product.price, " تومان ")
            previousPriceTv.text = formatPrice(product.previous_price, " تومان ")
            previousPriceTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            item.implementSpringAnimationTrait()

            itemView.setOnClickListener {
                itemClickListener?.onClick(product)
            }

            favoriteBtn.setOnClickListener {
                favoriteBtn.setImageResource(
                    if (product.isFavorite) R.drawable.ic_favorite_fill else R.drawable.ic_favorites
                )
                itemClickListener?.onFavoriteButtonClick(product)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}