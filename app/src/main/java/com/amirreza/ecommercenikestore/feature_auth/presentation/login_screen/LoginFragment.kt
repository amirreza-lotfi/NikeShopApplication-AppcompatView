package com.amirreza.ecommercenikestore.feature_auth.presentation.login_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amirreza.ecommercenikestore.databinding.FragmentLoginBinding
import com.amirreza.ecommercenikestore.feature_auth.presentation.AuthViewModel
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeCompletable
import com.amirreza.ecommercenikestore.feature_store.common.base.NikeFragment
import com.sevenlearn.nikestore.common.asyncIoNetworkCall
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment:NikeFragment() {
    lateinit var binding:FragmentLoginBinding
    private val authViewModel by viewModel<AuthViewModel>()
    private val compositeDisposable = CompositeDisposable()

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

        binding.loginBtn.setOnClickListener {
            val username = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            authViewModel.login(username,password)
                .asyncIoNetworkCall()
                .subscribe(object : NikeCompletable(compositeDisposable){
                    override fun onComplete() {
                        requireActivity().finish()
                    }
                })

        }

        binding.signUpBtn.setOnClickListener {

        }
    }
}