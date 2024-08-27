package com.anabell.words.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anabell.words.MyApplication
import com.anabell.words.ui.MainActivity
import com.anabell.words.ui.auth.login.LoginActivity

class AuthCheckerActivity : AppCompatActivity() {

    private val viewModel by viewModels<AuthCheckerViewModel>() {
        (application as MyApplication).viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isLoggedIn.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

        viewModel.checkLogin()
    }

}