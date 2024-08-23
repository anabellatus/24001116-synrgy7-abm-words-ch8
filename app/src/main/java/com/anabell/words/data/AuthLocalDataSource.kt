package com.anabell.words.data

interface AuthLocalDataSource {

    fun saveToken(token: String)

    fun loadToken(): String?

    fun clearToken()

    fun saveUserName(name: String)

    fun saveUserEmail(email: String)

    fun loadUserName(): String

    fun loadUserEmail(): String
}