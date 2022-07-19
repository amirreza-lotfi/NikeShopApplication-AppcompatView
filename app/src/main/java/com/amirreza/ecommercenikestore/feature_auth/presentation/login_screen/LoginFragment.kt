package com.amirreza.ecommercenikestore.feature_auth.presentation.login_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amirreza.ecommercenikestore.databinding.FragmentLoginBinding
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeFragment

class LoginFragment:NikeFragment() {
    lateinit var binding:FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}