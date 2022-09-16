package com.amirreza.ecommercenikestore.features.feature_profile.presentation.order_history_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentOrderHistoryBinding
import com.amirreza.ecommercenikestore.features.feature_store.common.base.NikeFragment
import com.amirreza.ecommercenikestore.features.feature_store.common.util.getVerticalLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_order_history.*
import org.koin.android.ext.android.inject

class OrderHistoryFragment : NikeFragment() {
    private lateinit var binding: FragmentOrderHistoryBinding
    private val orderHistoryViewModel:OrderHistoryViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderHistoryViewModel.progressBarIndicatorLiveData.observe(viewLifecycleOwner){
            setProgressBarIndicator(it)
        }

        binding.orderHistoryRecyclerView.layoutManager = getVerticalLinearLayoutManager(requireContext())

        orderHistoryViewModel.orders.observe(viewLifecycleOwner) {
            binding.orderHistoryRecyclerView.adapter = OrderHistoryAdapter(it,requireContext())
        }

        toolbarView.onBackButtonClickListener = View.OnClickListener {
            findNavController().popBackStack()
        }
    }

}