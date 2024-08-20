package com.anabell.words.data.datasource.local

import android.content.SharedPreferences
import com.anabell.words.data.AuthLocalDataSource

class AuthLocalDataSourceImpl(
    private val sharedPreferences: SharedPreferences,
) : AuthLocalDataSource {

    companion object {
        const val KEY_TOKEN = "token"
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
}