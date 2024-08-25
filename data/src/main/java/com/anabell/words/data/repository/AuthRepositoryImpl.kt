package com.anabell.words.data.repository

import com.anabell.words.data.AuthLocalDataSource
import com.anabell.words.data.AuthRemoteDataSource
import com.anabell.words.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override suspend fun login(email: String, password: String): String {
        return authRemoteDataSource.login(email, password)
    }

    override suspend fun register(name: String, email: String, password: String): String {
        return authRemoteDataSource.register(name, email, password)
    }

    override suspend fun saveToken(token: String) {
        authLocalDataSource.saveToken(token)
    }

    override suspend fun loadToken(): String? {
        return authLocalDataSource.loadToken()
    }

    override suspend fun clearToken() {
        authLocalDataSource.clearToken()
    }

    override suspend fun saveUserName(name: String) {
        authLocalDataSource.saveUserName(name)
    }

    override suspend fun saveUserEmail(email: String) {
        authLocalDataSource.saveUserEmail(email)
    }

    override suspend fun loadUserName(): String? {
        return authLocalDataSource.loadUserName()
    }

    override suspend fun loadUserEmail(): String? {
        return authLocalDataSource.loadUserEmail()
    }
}