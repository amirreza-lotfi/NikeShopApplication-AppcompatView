package com.amirreza.ecommercenikestore.features.feature_profile.presentation.order_history_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.amirreza.ecommercenikestore.databinding.FragmentOrderHistoryBinding
import com.amirreza.ecommercenikestore.utils.base.NikeFragment
import com.amirreza.ecommercenikestore.utils.util.getVerticalLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_order_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderHistoryFragment : NikeFragment() {
    private lateinit var binding: FragmentOrderHistoryBinding
    private val orderHistoryViewModel:OrderHistoryViewModel by viewModel()

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