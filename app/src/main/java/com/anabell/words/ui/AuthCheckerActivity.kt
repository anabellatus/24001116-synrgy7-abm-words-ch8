package com.anabell.words.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AuthCheckerActivity : AppCompatActivity() {

    private val viewModel by viewModels<AuthCheckerViewModel>() {
        AuthCheckerViewModel.provideFactory(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (viewModel.checkLogin()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}