package com.anabell.words.domain

interface AuthRepository {

    fun login(email: String, password: String): String

    fun register(name: String, email: String, password: String): String

    fun saveToken(token: String)

    fun loadToken(): String?

    fun clearToken()

}