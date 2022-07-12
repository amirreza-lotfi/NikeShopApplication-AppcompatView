package com.amirreza.ecommercenikestore.presebtation.all_product_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.common.base.EXTRA_SORT_TYPE
import com.amirreza.ecommercenikestore.common.base.EXTRA_VIEW_TYPE
import com.amirreza.ecommercenikestore.common.base.NikeFragment
import com.amirreza.ecommercenikestore.databinding.FragmentAllCommentBinding
import com.amirreza.ecommercenikestore.databinding.FragmentAllProductBinding
import com.amirreza.ecommercenikestore.presebtation.all_product_fragment.product_recycler_view.AllProductListAdaper
import com.amirreza.ecommercenikestore.presebtation.home_fragment.product_list_util.ProductListAdapter
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.sevenlearn.nikestore.common.getGridLayoutManager
import com.sevenlearn.nikestore.common.getVerticalLinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AllProductFragment : NikeFragment() {
    lateinit var binding:FragmentAllProductBinding
    private val productListAdapter:AllProductListAdaper by inject {
        parametersOf(this.arguments?.get(EXTRA_VIEW_TYPE))
    }
    private val allProductViewModel:AllProductViewModel by inject{
        parametersOf(this.arguments?.get(EXTRA_SORT_TYPE))
    }

   private lateinit var recyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllProductBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setChangeViewTypeButtonClick()
    }

    private fun setUpRecyclerView(){
        recyclerView=binding.productRecyclerView
        setViewTypeImageView(R.drawable.ic_grid)
        recyclerView.layoutManager = getGridLayoutManager(requireContext())
        recyclerView.adapter = productListAdapter

        allProductViewModel.productList.observe(viewLifecycleOwner){
            productListAdapter.products = it as ArrayList<Product> /* = java.util.ArrayList<com.example.nikeshop.feature_shop.domain.entity.Product> */
        }
    }
    private fun setChangeViewTypeButtonClick(){
        binding.viewTypeChangerBtn.setOnClickListener{
            if(recyclerView.layoutManager is GridLayoutManager){
                recyclerView.layoutManager = getVerticalLinearLayoutManager(requireContext())
            }else{
                recyclerView.layoutManager = getGridLayoutManager(requireContext())
            }
        }
    }

    private fun setViewTypeImageView(res:Int){
        binding.viewTypeChangerBtn.setImageResource(res)
    }
}