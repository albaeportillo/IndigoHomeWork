package com.example.usecases.usecase

import com.indigoag.repository.network.auth.IAuthService
import com.indigoag.repository.network.auth.User
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginUseCase : KoinComponent {

    private val iAuthService : IAuthService by inject()

    suspend fun loadLogin(email: String, password: String): Result<User> {
      return iAuthService.login(email, password)
    }
}