package com.anabell.words.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anabell.words.MyApplication
import com.anabell.words.R
import com.anabell.words.databinding.ActivityRegisterBinding
import com.anabell.words.ui.MainActivity

class RegisterActivity : AppCompatActivity() {

    private val viewBinding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private val viewModel: RegisterViewModel by viewModels<RegisterViewModel> {
        (application as MyApplication).viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.backButton.setOnClickListener {
            finish()
        }

        viewBinding.registerButton.setOnClickListener {
            if (viewBinding.tieName.text.isNullOrEmpty()) {
                viewBinding.tieName.error = getString(R.string.name_error)
            } else if (viewBinding.tieEmail.text.isNullOrEmpty()) {
                viewBinding.tieEmail.error = getString(R.string.email_error)
            } else if (viewBinding.tiePassword.text.isNullOrEmpty()) {
                viewBinding.tiePassword.error = getString(R.string.password_error)
            } else if (viewBinding.tiePassword.text!!.length < 8) {
                viewBinding.tiePassword.error = getString(R.string.invalid_password)
            } else if (viewBinding.tieConfirmPassword.text!! != viewBinding.tiePassword.text) {
                viewBinding.tieConfirmPassword.error = getString(R.string.unmatch_password)
            } else {
                viewBinding.tieEmail.error = null
                viewBinding.tiePassword.error = null
                viewBinding.tieConfirmPassword.error = null

                viewBinding.registerButton.isEnabled = false

                viewModel.register(
                    name = viewBinding.tieName.text.toString(),
                    email = viewBinding.tieEmail.text.toString(),
                    password = viewBinding.tiePassword.text.toString()
                )
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                viewBinding.loading.visibility = View.VISIBLE
            } else {
                viewBinding.loading.visibility = View.GONE
            }
        }

        viewModel.success.observe(this) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        viewModel.error.observe(this) { throwable ->
            Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
        }

    }
}