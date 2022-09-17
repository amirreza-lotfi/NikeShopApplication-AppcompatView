package com.amirreza.ecommercenikestore.features.feature_auth.presentation.signup_screen;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.FragmentSignupBinding
import com.amirreza.ecommercenikestore.features.feature_auth.presentation.AuthViewModel
import com.amirreza.ecommercenikestore.features.feature_auth.presentation.login_screen.LoginFragment
import com.amirreza.ecommercenikestore.utils.base.NikeFragment
import com.amirreza.ecommercenikestore.utils.util.asyncIoNetworkCall
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SignUpFragment: NikeFragment() {
    lateinit var binding: FragmentSignupBinding
    private val authViewModel by viewModel<AuthViewModel>()
    private val compositeDisposable = CompositeDisposable()

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

        binding.signUpMaterialButton.setOnClickListener {
            val username = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            authViewModel.signUp(username,password)
                .asyncIoNetworkCall()
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("","")
                    }

                    override fun onComplete() {
                        requireActivity().finish()
                        showToast("ثبت نام با موفقیت انجام شد.")
                    }

                    override fun onError(e: Throwable) {
                        Timber.i(e)
                        showToast("ثبت نام انجام نشد. لطفا مجددا امتحان کنید.")
                    }
                })
        }

        binding.loginBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.auth_container,LoginFragment()
            ).commit()
        }
    }
}
