package com.indigoag.repository.network.auth

class AuthServiceImpl : IAuthService {
    override suspend fun login(email: String, password: String): Result<User> {
        email.lowercase()
            .takeIf { it.contains("indigo") }
            ?: return Result.failure(AuthError.UserNotFound)

        password
            .takeIf { it.isNotBlank() && it.length > 5 }
            ?: return Result.failure(AuthError.InvalidLogin)

        return Result.success(User(email))
    }

}