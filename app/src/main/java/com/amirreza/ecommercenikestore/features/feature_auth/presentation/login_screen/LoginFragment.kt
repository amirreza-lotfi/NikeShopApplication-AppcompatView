package com.amirreza.ecommercenikestore.features.feature_auth.presentation.login_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentLoginBinding
import com.amirreza.ecommercenikestore.features.feature_auth.presentation.AuthViewModel
import com.amirreza.ecommercenikestore.features.feature_auth.presentation.signup_screen.SignUpFragment
import com.amirreza.ecommercenikestore.utils.base.NikeCompletable
import com.amirreza.ecommercenikestore.utils.base.NikeFragment
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment: NikeFragment() {
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
                        showToast("ورود با موفقیت انجام شد.")
                    }

                    override fun onError(e: Throwable) {
                        showToast("اطلاعات وارده وجود ندارد. لطفا ثبت نام کنید.")
                    }

                })
        }

        binding.signUpBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.auth_container,SignUpFragment()
            ).commit()
        }
    }
}