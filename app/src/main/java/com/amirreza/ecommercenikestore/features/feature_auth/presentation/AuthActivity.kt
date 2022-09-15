package com.amirreza.ecommercenikestore.features.feature_auth.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.features.feature_auth.presentation.login_screen.LoginFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.auth_container,LoginFragment())
            }.commit()
    }
}