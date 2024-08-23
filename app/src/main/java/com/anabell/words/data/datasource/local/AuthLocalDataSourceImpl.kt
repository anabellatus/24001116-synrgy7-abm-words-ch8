package com.anabell.words.data.datasource.local

import android.content.SharedPreferences
import com.anabell.words.data.AuthLocalDataSource

class AuthLocalDataSourceImpl(
    private val sharedPreferences: SharedPreferences,
) : AuthLocalDataSource {

    companion object {
        const val KEY_TOKEN = "token"
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun loadToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    override fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }

    override fun saveUserName(name: String) {
        sharedPreferences.edit().putString(KEY_NAME, name).apply()
    }

    override fun saveUserEmail(email: String) {
        sharedPreferences.edit().putString(KEY_EMAIL, email).apply()
    }

    override fun loadUserName(): String {
        return sharedPreferences.getString(KEY_NAME, "") ?: "Anabell"
    }

    override fun loadUserEmail(): String {
        return sharedPreferences.getString(KEY_EMAIL, "") ?: ""
    }


}
