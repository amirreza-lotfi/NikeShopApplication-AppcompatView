package com.amirreza.ecommercenikestore.feature_store.presebtation.product_detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_ALL_COMMENTS
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeCompletable
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeFragment
import com.amirreza.ecommercenikestore.databinding.FragmentProductDetailBinding
import com.amirreza.ecommercenikestore.feature_store.domain.entity.Comment
import com.amirreza.ecommercenikestore.feature_store.domain.repository.ImageLoaderI
import com.amirreza.ecommercenikestore.feature_store.presebtation.product_detail_fragment.comment_recyclerView.CommentAdapter
import com.amirreza.ecommercenikestore.feature_store.presebtation.product_detail_fragment.scroll.ObservableScrollViewCallbacks
import com.amirreza.ecommercenikestore.feature_store.presebtation.product_detail_fragment.scroll.ScrollState
import com.google.android.material.snackbar.Snackbar
import com.sevenlearn.nikestore.common.asyncIoNetworkCall
import com.sevenlearn.nikestore.common.formatPrice
import io.reactivex.disposables.CompositeDisposable
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
            binding.previousPriceTv.text = formatPrice(it.previous_price)
            binding.currentPriceTv.text = formatPrice(it.price)
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
        productDetailViewModel.addProductToShoppingCart()
            .asyncIoNetworkCall()
            .subscribe(object : NikeCompletable(compositeDisposable){
                override fun onComplete() {
                    Snackbar
                        .make(rootView as CoordinatorLayout,"به سبد خرید اضافه شد",Snackbar.LENGTH_SHORT)
                        .show()
                }
            })
    }

}