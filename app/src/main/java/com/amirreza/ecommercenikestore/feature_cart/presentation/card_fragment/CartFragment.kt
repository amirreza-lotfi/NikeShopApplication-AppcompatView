package com.amirreza.ecommercenikestore.feature_cart.presentation.card_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentCartBinding
import com.amirreza.ecommercenikestore.feature_cart.domain.entity.cart.CartItem
import com.amirreza.ecommercenikestore.feature_cart.presentation.card_fragment.cartItemAdapter.CartItemAdapter
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_PRODUCT_FROM_HOME_TO_DETAIL
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeFragment
import com.amirreza.ecommercenikestore.feature_store.domain.repository.ImageLoaderI
import com.sevenlearn.nikestore.common.asyncIoNetworkCall
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

class CartFragment:NikeFragment(),CartItemCallBack{
    private lateinit var binding: FragmentCartBinding
    private val cartViewModel:CartViewModel by inject()
    private val imageLoader:ImageLoaderI by inject()
    private lateinit var cartAdapter:CartItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cartItemRecyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        setProgressBarVisibility()

        cartViewModel.cartResponse.observe(viewLifecycleOwner){ itemList->
            cartAdapter = CartItemAdapter(itemList as MutableList<CartItem>,imageLoader,this)
            binding.cartItemRecyclerView.adapter = cartAdapter
        }

        cartViewModel.purchaseDetailOfCart.observe(viewLifecycleOwner){ purchaseDetail->
            cartAdapter?.let{
                it.purchaseDetail = purchaseDetail
                it.notifyItemChanged(cartAdapter.cartItemList.size)
            }
        }

    }

    private fun setProgressBarVisibility(){
        cartViewModel.progressBarIndicatorLiveData.observe(viewLifecycleOwner){ mustShow->
            setProgressBarIndicator(mustShow)
        }
    }

    override fun onStart() {
        super.onStart()
        cartViewModel.refresh()
    }

    override fun onDeleteCartItemClicked(cartItem: CartItem) {
        cartViewModel.removeItemFromCart(cartItem)
            .asyncIoNetworkCall()
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }
                override fun onComplete() {
                    cartAdapter.removeItem(cartItem)
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun onIncreaseItemCountClicked(cartItem: CartItem) {
        cartViewModel.increaseCartItemCount(cartItem)
            .asyncIoNetworkCall()
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }
                override fun onComplete() {
                    cartAdapter.changesCount(cartItem)
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun onDecreaseItemCountClicked(cartItem: CartItem) {
        cartViewModel.decreaseCartItemCount(cartItem)
            .asyncIoNetworkCall()
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }
                override fun onComplete() {
                    cartAdapter.changesCount(cartItem)
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun onProductImageClicked(cartItem: CartItem) {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_PRODUCT_FROM_HOME_TO_DETAIL,cartItem.product)
        findNavController().navigate(R.id.action_navigation_cart_to_productDetailFragment, bundle)
    }
}