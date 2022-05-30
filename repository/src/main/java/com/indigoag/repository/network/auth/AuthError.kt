package com.indigoag.repository.network.auth

sealed class AuthError : Exception() {
    object UserNotFound : AuthError()
    object InvalidLogin : AuthError()
}
