package com.amirreza.ecommercenikestore.presebtation.product_detail_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentProductDetailBinding
import com.amirreza.ecommercenikestore.domain.entity.Comment
import com.amirreza.ecommercenikestore.domain.repository.ImageLoaderI
import com.amirreza.ecommercenikestore.presebtation.product_detail_fragment.comment_recyclerView.CommentAdapter
import com.amirreza.ecommercenikestore.presebtation.product_detail_fragment.scroll.ObservableScrollView
import com.amirreza.ecommercenikestore.presebtation.product_detail_fragment.scroll.ObservableScrollViewCallbacks
import com.amirreza.ecommercenikestore.presebtation.product_detail_fragment.scroll.ScrollState
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.sevenlearn.nikestore.common.formatPrice
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProductDetailFragment : Fragment() {
    lateinit var binding:FragmentProductDetailBinding
    private val productDetailViewModel:ProductDetailViewModel by inject {
        parametersOf(this.arguments)
    }
    private val imageLoaderI:ImageLoaderI by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCommentsRecyclerView()

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
                    binding.toolbarTitleTv.alpha = (scrollY.toFloat() / heightOfImage.toFloat())
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
        val adapter = CommentAdapter()
        commentRecyclerView.adapter = adapter

        productDetailViewModel.commentsLiveData.observe(viewLifecycleOwner){ comments->
            adapter.comments = comments as ArrayList<Comment> /* = java.util.ArrayList<com.amirreza.ecommercenikestore.domain.entity.Comment> */

        }

    }

}