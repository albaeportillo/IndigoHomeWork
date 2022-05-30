package com.indigoag.android.homework.ui.login

sealed class LoginViewModelState {
    object InProgress : LoginViewModelState()
    object IsSuccess : LoginViewModelState()
    object IsFailure: LoginViewModelState()
}