package com.amirreza.ecommercenikestore.feature_cart.presentation.card_fragment.cartItemAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.ItemCartBinding
import com.amirreza.ecommercenikestore.databinding.ItemPurchesDetailBinding
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartItem
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.PurchaseDetail
import com.amirreza.ecommercenikestore.feature_cart.presentation.card_fragment.CartItemCallBack
import com.amirreza.ecommercenikestore.feature_store.data.repository.FrescoImageLoadingService
import com.amirreza.ecommercenikestore.feature_store.domain.repository.ImageLoaderI
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_purches_detail.view.*
import org.w3c.dom.Text

const val VIEW_TYPE_CART = 0
const val VIEW_TYPE_PURCHASE_DETAIL = 0

class CartItemAdapter(
    val cartItemList:MutableList<CartItem> = mutableListOf(),
    private val imageLoadingService: ImageLoaderI,
    private val cartItemCallBack: CartItemCallBack,
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var purchaseDetail:PurchaseDetail?=null


    inner class CartItemHolder(private val binding: ItemCartBinding):RecyclerView.ViewHolder(binding.root){

        fun onBind(cartItem: CartItem){
            binding.productTitleTv.text = cartItem.product.title
            imageLoadingService.load(binding.productIV,cartItem.product.image)
            binding.cartItemCountTv.text = cartItem.count.toString()
            binding.previousPriceProductTv.text = cartItem.product.previous_price.toString()
            binding.priceProductTv.text = cartItem.product.price.toString()

            binding.progressBarChangeCount.visibility = if (cartItem.progressBarVisibility) View.VISIBLE else View.GONE
            binding.cartItemCountTv.visibility = if (cartItem.progressBarVisibility) View.INVISIBLE else View.VISIBLE
            if(cartItem.progressBarVisibility){
                binding.progressBarChangeCount.visibility = View.VISIBLE
                binding.cartItemCountTv.visibility = View.INVISIBLE
            }

            binding.removeFromCartBtn.setOnClickListener {
                cartItemCallBack.onDeleteCartItemClicked(cartItem)
            }

            binding.productIV.setOnClickListener {
                cartItemCallBack.onProductImageClicked(cartItem)
            }

            binding.increaseBtn.setOnClickListener{
                cartItem.progressBarVisibility = true
                binding.progressBarChangeCount.visibility = View.VISIBLE
                binding.cartItemCountTv.visibility = View.INVISIBLE
                cartItemCallBack.onIncreaseItemCountClicked(cartItem)
            }

            binding.decreaseBtn.setOnClickListener{
                if(cartItem.count>1) {
                    cartItem.progressBarVisibility = false
                    binding.progressBarChangeCount.visibility = View.VISIBLE
                    binding.cartItemCountTv.visibility = View.INVISIBLE
                    cartItemCallBack.onDecreaseItemCountClicked(cartItem)
                }
            }
        }
    }

    inner class PurchaseDetailHolder(private val binding:ItemPurchesDetailBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(totalPrice:Int, shippingCost:Int, payablePrice:Int){
            binding.totalPriceTV.text = totalPrice.toString()
            binding.shippingCostTV.text = shippingCost.toString()
            binding.payablePriceTV.text = payablePrice.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)//.inflate(R.layout.item_purches_detail,parent,false)
        if(viewType == VIEW_TYPE_CART){
            val binding = ItemCartBinding.inflate(layoutInflater,parent,false)
            return CartItemHolder(binding)
        }
        val binding = ItemPurchesDetailBinding.inflate(layoutInflater,parent,false)
        return PurchaseDetailHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        if(position == cartItemList.size)
            return VIEW_TYPE_PURCHASE_DETAIL
        return VIEW_TYPE_CART
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CartItemHolder){
            holder.onBind(cartItemList[position])
        }else if(holder is PurchaseDetailHolder){
            purchaseDetail?.let {
                holder.bind(it.totalPrice,it.deliveryCost,it.payAblePrice)
            }
        }
    }

    fun removeItem(cartItem: CartItem){
        val indexOfCartItem = cartItemList.indexOf(cartItem)

        if(indexOfCartItem > -1){
            cartItemList.removeAt(indexOfCartItem)
            notifyItemRemoved(indexOfCartItem)
        }
    }

    fun changesCount(cartItem: CartItem){
        val indexOfCartItem = cartItemList.indexOf(cartItem)

        if(indexOfCartItem > -1){
            cartItemList[indexOfCartItem].progressBarVisibility = false
            notifyItemChanged(indexOfCartItem)
        }
    }


    override fun getItemCount(): Int {
        return cartItemList.size+1
    }

}