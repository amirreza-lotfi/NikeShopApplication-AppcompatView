package com.amirreza.ecommercenikestore.features.feature_profile.presentation.profile_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentProfileBinding
import com.amirreza.ecommercenikestore.features.feature_auth.presentation.AuthActivity
import com.amirreza.ecommercenikestore.features.feature_store.common.base.NikeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment:NikeFragment() {
    private lateinit var binding:FragmentProfileBinding
    private val profileViewModel:ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderHistoryBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_orderHistoryFragment)
        }
        binding.favoriteProductBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_favoritesFragment)
        }
    }
    override fun onStart() {
        super.onStart()
        checkUserAuthentication()
    }

    private fun checkUserAuthentication(){
        if(profileViewModel.isSignUpped){
            binding.userEmail.text = profileViewModel.username
            binding.loginLogOutTextView.text = "خروج از حساب کاربری"
            binding.loginLogOutTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            binding.loginLogOutTextView.setOnClickListener {
                profileViewModel.logOut()
                checkUserAuthentication()
            }

        }else{
            binding.userEmail.text = "کاربر مهمان"
            binding.loginLogOutTextView.text = "ورود به حساب کاربری"
            binding.loginLogOutTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
            binding.loginLogOutTextView.setOnClickListener {
                startActivity(
                    Intent(
                        requireContext(),
                        AuthActivity::class.java
                    )
                )
            }
        }
    }
}