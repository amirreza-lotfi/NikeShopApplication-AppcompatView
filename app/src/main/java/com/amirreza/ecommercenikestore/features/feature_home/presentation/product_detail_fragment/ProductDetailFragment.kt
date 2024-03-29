package com.amirreza.ecommercenikestore.features.feature_home.presentation.product_detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.utils.util.EXTRA_ALL_COMMENTS
import com.amirreza.ecommercenikestore.utils.base.NikeCompletable
import com.amirreza.ecommercenikestore.utils.base.NikeFragment
import com.amirreza.ecommercenikestore.databinding.FragmentProductDetailBinding
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Comment
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.ImageLoaderI
import com.amirreza.ecommercenikestore.features.feature_home.presentation.product_detail_fragment.comment_recyclerView.CommentAdapter
import com.amirreza.ecommercenikestore.features.feature_home.presentation.product_detail_fragment.scroll.ObservableScrollViewCallbacks
import com.amirreza.ecommercenikestore.features.feature_home.presentation.product_detail_fragment.scroll.ScrollState
import com.google.android.material.snackbar.Snackbar
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall
import com.amirreza.ecommercenikestore.utils.util.formatPrice
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProductDetailFragment : NikeFragment() {
    lateinit var binding:FragmentProductDetailBinding
    private val productDetailViewModel:ProductDetailViewModel by inject {parametersOf(this.arguments)}
    private val imageLoaderI:ImageLoaderI by inject()
    private val commentAdapter = CommentAdapter(false)
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpProgressBar()
        setUpCommentsRecyclerView()
        setUpAllCommentButtonVisibility()
        setOnClickAddToCartButton()

        productDetailViewModel.productLiveData.observe(viewLifecycleOwner){
            imageLoaderI.load(binding.productImage,it.image)
            binding.productTitle.text = it.title
            binding.previousPriceTv.text = formatPrice(it.previous_price, " تومان ")
            binding.currentPriceTv.text = formatPrice(it.price, " تومان ")
            binding.toolbarTitleTv.text = it.title
        }

        binding.productImage.post {
            val heightOfImage = binding.productImage.height
            binding.observableScrollView.addScrollViewCallbacks(object: ObservableScrollViewCallbacks{
                override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
                    binding.toolbarView.alpha = (scrollY.toFloat() / heightOfImage.toFloat())
                    binding.productImage.translationY = scrollY.toFloat()/2
                }
                override fun onDownMotionEvent() {}
                override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {}
            })
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.favoriteBtn.setOnClickListener {
            productDetailViewModel.addOrDeleteProductFromFavorite()
        }

        productDetailViewModel.isFavorite.observe(viewLifecycleOwner){
            if(it)
                binding.favoriteBtn.setImageResource(R.drawable.ic_favorite_fill)
            else
                binding.favoriteBtn.setImageResource(R.drawable.ic_favorite_24)
        }
    }

    private fun setUpCommentsRecyclerView(){
        val commentRecyclerView = binding.commentsRv
        commentRecyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        commentRecyclerView.isNestedScrollingEnabled = false
        commentRecyclerView.adapter = commentAdapter

        productDetailViewModel.commentsLiveData.observe(viewLifecycleOwner){ comments->
            commentAdapter.comments = comments as ArrayList<Comment>
            onSeeAllCommentsClick()
        }

    }
    private fun setUpAllCommentButtonVisibility(){
        binding.viewAllComment.visibility = if(!commentAdapter.mustAllCommentShow()) View.VISIBLE else View.GONE
    }
    private fun onSeeAllCommentsClick(){
        binding.viewAllComment.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(EXTRA_ALL_COMMENTS,productDetailViewModel.productLiveData.value!!.id)
            findNavController().navigate(R.id.action_productDetailFragment_to_allCommentFragment3,bundle)
        }
    }

    private fun setUpProgressBar(){
        productDetailViewModel.progressBarIndicatorLiveData.observe(viewLifecycleOwner){
            setProgressBarIndicator(it)
        }
    }
    private fun setOnClickAddToCartButton(){
        binding.addToCartBtn.setOnClickListener {
            productDetailViewModel.addProductToShoppingCart()
                .asyncIoNetworkCall()
                .subscribe(object : NikeCompletable(compositeDisposable){
                    override fun onComplete() {
                        Snackbar
                            .make(rootView as CoordinatorLayout,"به سبد خرید اضافه شد",Snackbar.LENGTH_SHORT)
                            .show()
                        val countOfCartItem = EventBus.getDefault().getStickyEvent(ProductCountInShoppingCart::class.java)
                        countOfCartItem?.let {
                            it.count += 1
                            EventBus.getDefault().postSticky(it)
                        }
                    }
                })
        }

    }

}