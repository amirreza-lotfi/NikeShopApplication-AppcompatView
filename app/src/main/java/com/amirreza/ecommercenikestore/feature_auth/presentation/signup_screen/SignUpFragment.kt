package com.amirreza.ecommercenikestore.feature_auth.presentation.signup_screen;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amirreza.ecommercenikestore.databinding.FragmentLoginBinding
import com.amirreza.ecommercenikestore.databinding.FragmentSignupBinding
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeFragment

class SignUpFragment:NikeFragment() {
    lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
