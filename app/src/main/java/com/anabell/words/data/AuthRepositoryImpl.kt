package com.anabell.words.data

import com.anabell.words.domain.AuthRepository

class AuthRepositoryImpl(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override fun login(email: String, password: String): String {
        return authRemoteDataSource.login(email, password)
    }

    override fun register(name: String, email: String, password: String): String {
        return authRemoteDataSource.register(name, email, password)
    }

    override fun saveToken(token: String) {
        authLocalDataSource.saveToken(token)
    }

    override fun loadToken(): String? {
        return authLocalDataSource.loadToken()
    }

    override fun clearToken() {
        authLocalDataSource.clearToken()
    }
}