package com.indigoag.android.homework.ui.login


import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.usecases.usecase.LoginUseCase
import com.indigoag.android.homework.R
import com.indigoag.android.homework.ui.BaseViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginViewModel : BaseViewModel(), KoinComponent {

    private val loginUseCase: LoginUseCase by inject()

    private val _loginForm = MutableLiveData<LoginFormState?>()
    val loginFormState: LiveData<LoginFormState?> = _loginForm

    private val _loginViewState = MutableLiveData<LoginViewModelState?>()
    val loginViewState: LiveData<LoginViewModelState?> get() = _loginViewState


    fun login(email: String, pass: String) = executeSuspend {
        _loginViewState.postValue(LoginViewModelState.InProgress)
        val response = loginUseCase.loadLogin(email, pass)
        when {
            response.isSuccess -> {
                _loginViewState.postValue(LoginViewModelState.IsSuccess)
            }
            response.isFailure -> {
                _loginViewState.postValue(LoginViewModelState.IsFailure)
            }
            else -> {
                _loginViewState.postValue(LoginViewModelState.IsFailure)
            }
        }
    }

    fun resetViewModelsState() {
        _loginViewState.value = null
        _loginForm.value = null
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        var condition = false
        if (username.contains('@')) {
            condition = Patterns.EMAIL_ADDRESS.matcher(username).matches()
        }
        return condition
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}