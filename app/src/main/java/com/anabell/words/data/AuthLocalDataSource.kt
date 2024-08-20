package com.anabell.words.data

interface AuthLocalDataSource {

    fun saveToken(token: String)

    fun loadToken(): String?

    fun clearToken()
}