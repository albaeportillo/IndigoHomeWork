package com.indigoag.android.homework.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.indigoag.android.homework.R
import com.indigoag.android.homework.component.ProgressFull
import com.indigoag.android.homework.databinding.ActivityLoginBinding
import com.indigoag.android.homework.ui.home.HomeActivity
import com.indigoag.android.homework.util.afterTextChanged
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var activityLoginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        activityLoginBinding.viewModel = loginViewModel
        activityLoginBinding.lifecycleOwner = this

        setUpView()
        observeLiveData()
    }

    private fun observeLiveData() {
        loginViewModel.loginViewState.observe(this) {
            it?.let {
                onLoginViewModelState(it)
            }

        }

        loginViewModel.loginFormState.observe(this) {
            it?.let {
                onLoginFormState(it)
            }
        }

    }

    private fun setUpView() {
        with(activityLoginBinding) {
            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }

            username.afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            password.apply {
                afterTextChanged {
                    loginViewModel.loginDataChanged(
                        username.text.toString(),
                        password.text.toString()
                    )
                }
            }

        }
    }

    private fun onLoginViewModelState(loginViewModelState: LoginViewModelState) {
        when (loginViewModelState) {
            LoginViewModelState.IsSuccess -> {
                Toast.makeText(this, resources.getString(R.string.welcome), Toast.LENGTH_LONG)
                    .show()
                setLoginVisibility(true)
                goToHome()
            }
            LoginViewModelState.IsFailure -> {
                Toast.makeText(this, resources.getString(R.string.error), Toast.LENGTH_LONG).show()
                setLoginVisibility(false)
            }
            LoginViewModelState.InProgress -> {
                ProgressFull.crossProgressFull(this)
                setLoginVisibility(true)
            }
        }
    }

    private fun onLoginFormState(loginFormState: LoginFormState) {
        activityLoginBinding.login.isEnabled = loginFormState.isDataValid

        if (loginFormState.usernameError != null) {
            activityLoginBinding.username.error = getString(loginFormState.usernameError)
        }
        if (loginFormState.passwordError != null) {
            activityLoginBinding.password.error = getString(loginFormState.passwordError)
        }
    }

    private fun setLoginVisibility(visibility: Boolean) {
        activityLoginBinding.loading.isVisible = visibility
    }

    private fun goToHome() {
        loginViewModel.resetViewModelsState()
        HomeActivity.start(this)
        setLoginVisibility(false)
    }

}
