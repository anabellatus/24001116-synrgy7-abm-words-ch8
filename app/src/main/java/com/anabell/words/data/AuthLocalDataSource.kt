package com.anabell.words.data

interface AuthLocalDataSource {

    suspend fun saveToken(token: String)

    suspend fun loadToken(): String?

    suspend fun clearToken()

    suspend fun saveUserName(name: String)

    suspend fun saveUserEmail(email: String)

    suspend fun loadUserName(): String?

    suspend fun loadUserEmail(): String?
}