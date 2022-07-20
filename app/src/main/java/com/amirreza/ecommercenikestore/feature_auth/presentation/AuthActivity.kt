package com.amirreza.ecommercenikestore.feature_auth.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.feature_auth.presentation.signup_screen.SignUpFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.auth_container,SignUpFragment())
        }.commit()
    }
}