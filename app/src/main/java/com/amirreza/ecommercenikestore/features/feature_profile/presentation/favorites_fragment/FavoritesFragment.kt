package com.amirreza.ecommercenikestore.features.feature_profile.presentation.favorites_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentFavoritesBinding
import com.amirreza.ecommercenikestore.utils.base.NikeFragment
import com.amirreza.ecommercenikestore.utils.util.EXTRA_KEY_DATA
import com.amirreza.ecommercenikestore.utils.util.getVerticalLinearLayoutManager
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Product
import com.amirreza.ecommercenikestore.features.feature_home.domain.repository.ImageLoaderI
import org.koin.android.ext.android.inject

class FavoritesFragment : NikeFragment(), FavoriteEvents {
    private lateinit var binding:FragmentFavoritesBinding
    private val viewModel:FavoriteViewModel by viewModels()
    private val imageLoaderI:ImageLoaderI by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritesProducts.layoutManager = getVerticalLinearLayoutManager(requireContext())

        viewModel.favoriteProducts.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.favoriteListEmpty.visibility = View.VISIBLE
            }else{
                binding.favoriteListEmpty.visibility = View.GONE
                binding.favoritesProducts.adapter = FavoriteListAdapter(it as MutableList<Product>, this, imageLoaderI)
            }
        }
    }

    override fun onLongClick(product: Product, size: Int) {
        viewModel.removeFromFavorite(product,size)
    }

    override fun onClick(product: Product) {
        findNavController().navigate(R.id.action_favoritesFragment_to_productDetailFragment,Bundle().apply { putParcelable(
            EXTRA_KEY_DATA,product)
        })
    }

}