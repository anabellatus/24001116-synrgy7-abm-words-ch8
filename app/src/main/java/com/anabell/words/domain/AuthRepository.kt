package com.anabell.words.domain

interface AuthRepository {

    suspend fun login(email: String, password: String): String

    suspend fun register(name: String, email: String, password: String): String

    suspend fun saveToken(token: String)

    suspend fun loadToken(): String?

    suspend fun clearToken()

    suspend fun saveUserName(name: String)

    suspend fun saveUserEmail(email: String)

    suspend fun loadUserName(): String?

    suspend fun loadUserEmail(): String?

}