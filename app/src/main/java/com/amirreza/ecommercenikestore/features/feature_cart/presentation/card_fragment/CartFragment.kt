package com.amirreza.ecommercenikestore.features.feature_cart.presentation.card_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentCartBinding
import com.amirreza.ecommercenikestore.features.feature_auth.presentation.AuthActivity
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.CartItem
import com.amirreza.ecommercenikestore.features.feature_cart.presentation.card_fragment.cartItemAdapter.CartItemAdapter
import com.amirreza.ecommercenikestore.features.feature_cart.presentation.card_fragment.cartItemAdapter.CartItemCallBack
import com.amirreza.ecommercenikestore.utils.util.EXTRA_PRODUCT_FROM_HOME_TO_DETAIL
import com.amirreza.ecommercenikestore.utils.util.EXTRA_PURCHASE_DETAIL
import com.amirreza.ecommercenikestore.utils.base.NikeFragment
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.ImageLoaderI
import com.amirreza.ecommercenikestore.utils.util.formatPrice
import com.google.android.material.button.MaterialButton
import com.amirreza.ecommercenikestore.utils.util.getVerticalLinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject

class CartFragment : NikeFragment(), CartItemCallBack {
    private lateinit var binding: FragmentCartBinding
    private val cartViewModel: CartViewModel by inject()
    private val imageLoader: ImageLoaderI by inject()
    private lateinit var cartAdapter: CartItemAdapter
    val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cartItemRecyclerView.layoutManager = getVerticalLinearLayoutManager(requireContext())
        setProgressBarVisibility()

        cartViewModel.purchaseDetailAndPurchaseButtonMustShow.observe(viewLifecycleOwner){ mustShow->
            if(mustShow){
                binding.paymentButton.visibility = View.VISIBLE
                binding.purchaseDetailLayout.visibility = View.VISIBLE
            }else{
                binding.paymentButton.visibility = View.GONE
                binding.purchaseDetailLayout.visibility = View.GONE
            }
        }

        cartViewModel.cartItems.observe(viewLifecycleOwner) { itemList ->
            cartAdapter = CartItemAdapter(itemList as MutableList<CartItem>, imageLoader, this)
            binding.cartItemRecyclerView.adapter = cartAdapter
        }

        cartViewModel.purchaseDetailOfCart.observe(viewLifecycleOwner) { purchaseDetail ->
            binding.totalPriceTV.text = formatPrice(purchaseDetail.totalPrice," تومان ")
            binding.shippingCostTV.text = formatPrice(purchaseDetail.deliveryCost," تومان ")
            binding.payablePriceTV.text = formatPrice(purchaseDetail.payAblePrice," تومان ")
        }

        cartViewModel.emptyCartState.observe(viewLifecycleOwner) { emptyState ->
            if (emptyState.mustShow) {
                val emptyView = getEmptyState(R.layout.view_emty_state_cart)
                emptyView?.let { emptyStateView ->
                    emptyStateView.visibility = View.VISIBLE

                    val message = emptyStateView.findViewById<TextView>(R.id.emptyStateMessageTv)
                    val emptyStateCtaBtn =
                        emptyStateView.findViewById<MaterialButton>(R.id.emptyStateCtaBtn)

                    emptyStateCtaBtn.visibility =
                        if (emptyState.mustShowActionButton) View.VISIBLE else View.GONE
                    emptyStateCtaBtn.setOnClickListener {
                        val intent = Intent(context, AuthActivity::class.java)
                        startActivity(intent)
                    }
                    message.text = getString(emptyState.messageResId)
                }
            } else {
                view.findViewById<FrameLayout>(R.id.rootOfEmptyState)?.visibility = View.GONE
            }
        }

        binding.paymentButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_PURCHASE_DETAIL,cartViewModel.purchaseDetailOfCart.value)
            findNavController().navigate(R.id.action_navigation_cart_to_checkoutFragment, bundle)
        }
    }

    private fun setProgressBarVisibility() {
        cartViewModel.progressBarIndicatorLiveData.observe(viewLifecycleOwner) { mustShow ->
            setProgressBarIndicator(mustShow)
        }
    }

    override fun onStart() {
        super.onStart()
        cartViewModel.refresh()
    }

    override fun onDeleteCartItemClicked(cartItem: CartItem) {
        cartViewModel.uiEvent(
            CartUiEvent.OnDeleteCartItemClicked(
                cartItem = cartItem,
                onCompleteEvent = {
                    cartAdapter.removeItem(cartItem)
                }
            )
        )
    }

    override fun onIncreaseItemCountClicked(cartItem: CartItem) {
        cartViewModel.uiEvent(
            CartUiEvent.OnIncreaseItemCountClicked(
                cartItem = cartItem,
                onCompleteEvent = {
                    cartAdapter.changesCount(cartItem)
                }
            )
        )
    }

    override fun onDecreaseItemCountClicked(cartItem: CartItem) {
        cartViewModel.uiEvent(
            CartUiEvent.OnDecreaseItemCountClicked(
                cartItem = cartItem,
                onCompleteEvent = {
                    cartAdapter.changesCount(cartItem)
                }
            )
        )
    }

    override fun onProductImageClicked(cartItem: CartItem) {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_PRODUCT_FROM_HOME_TO_DETAIL, cartItem.product)
        findNavController().navigate(R.id.action_navigation_cart_to_productDetailFragment, bundle)
    }
}