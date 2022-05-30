package com.indigoag.repository.network.auth

interface IAuthService {
    suspend fun login(email: String, password: String): Result<User>
}