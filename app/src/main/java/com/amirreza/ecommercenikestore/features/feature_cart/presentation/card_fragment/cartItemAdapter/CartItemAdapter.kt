package com.amirreza.ecommercenikestore.features.feature_cart.presentation.card_fragment.cartItemAdapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.databinding.ItemCartBinding
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartItem
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.ImageLoaderI

class CartItemAdapter(
    private val cartItemList:MutableList<CartItem> = mutableListOf(),
    private val imageLoadingService: ImageLoaderI,
    private val cartItemCallBack: CartItemCallBack,
):RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {

    inner class CartItemHolder(private val binding: ItemCartBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(cartItem: CartItem){
            binding.productTitleTv.text = cartItem.product.title
            imageLoadingService.load(binding.productIV,cartItem.product.image)
            binding.cartItemCountTv.text = cartItem.count.toString()
            binding.previousPriceProductTv.text = "${cartItem.product.getRealPrice()} تومان "
            binding.priceProductTv.text = "${cartItem.product.getPayablePrice()} تومان "
            binding.previousPriceProductTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

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

                }else{
                    cartItemCallBack.onDeleteCartItemClicked(cartItem)
                    removeItem(cartItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(layoutInflater,parent,false)
        return CartItemHolder(binding)
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
        return cartItemList.size
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        holder.onBind(cartItemList[position])
    }
}