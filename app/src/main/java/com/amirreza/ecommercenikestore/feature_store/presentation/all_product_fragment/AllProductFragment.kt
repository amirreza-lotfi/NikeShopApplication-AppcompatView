package com.amirreza.ecommercenikestore.feature_store.presentation.all_product_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_SORT_TYPE
import com.amirreza.ecommercenikestore.feature_store.common.base.EXTRA_VIEW_TYPE
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeFragment
import com.amirreza.ecommercenikestore.databinding.FragmentAllProductBinding
import com.amirreza.ecommercenikestore.feature_store.presentation.all_product_fragment.product_recycler_view.AllProductListAdaper
import com.example.nikeshop.feature_shop.domain.entity.Product
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.amirreza.ecommercenikestore.feature_store.common.util.getGridLayoutManager
import com.amirreza.ecommercenikestore.feature_store.common.util.getVerticalLinearLayoutManager
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
        setSortTypeTitle()
        setUpRecyclerView()
        setChangeViewTypeButtonClick()
        setUpProgressbar()
        setSortTypeClickListener()
    }
    private fun setSortTypeTitle(){
        binding.sortTypeTv.text = resources.getStringArray(R.array.sortType)[allProductViewModel.sortType]
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
                setViewTypeImageView(R.drawable.ic_baseline_list_24)
                recyclerView.layoutManager = getVerticalLinearLayoutManager(requireContext())
            }else{
                setViewTypeImageView(R.drawable.ic_grid)
                recyclerView.layoutManager = getGridLayoutManager(requireContext())
            }
        }
    }
    private fun setViewTypeImageView(res:Int){
        binding.viewTypeChangerBtn.setImageResource(res)
    }
    private fun setUpProgressbar(){
        allProductViewModel.progressBarIndicatorLiveData.observe(viewLifecycleOwner){
            setProgressBarIndicator(it)
        }
    }
    private fun setSortTypeClickListener(){
        binding.sortTypeClicked.setOnClickListener{
            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle("مرتب سازی")
                .setSingleChoiceItems(R.array.sortType,allProductViewModel.sortType
                ) { dialog, selectedIndex ->
                    dialog.dismiss()
                    allProductViewModel.selectedIndexChanged(selectedIndex)
                    binding.sortTypeTv.text = resources.getStringArray(R.array.sortType)[selectedIndex]
                }
            dialog.show()


        }
    }
}