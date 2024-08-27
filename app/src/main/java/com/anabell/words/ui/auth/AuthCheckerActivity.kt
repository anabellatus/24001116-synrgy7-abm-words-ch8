package com.anabell.words.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anabell.words.ui.MainActivity
import com.anabell.words.ui.auth.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthCheckerActivity : AppCompatActivity() {

    private val viewModel by viewModel<AuthCheckerViewModel>()

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