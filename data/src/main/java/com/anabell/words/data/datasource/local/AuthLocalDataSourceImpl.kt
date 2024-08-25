package com.anabell.words.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.anabell.words.data.AuthLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AuthLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
) : AuthLocalDataSource {

    companion object {
        const val KEY_TOKEN = "token"
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
        private val DATASTORE_KEY_TOKEN = stringPreferencesKey(KEY_TOKEN)
        private val DATASTORE_KEY_NAME = stringPreferencesKey(KEY_NAME)
        private val DATASTORE_KEY_EMAIL = stringPreferencesKey(KEY_EMAIL)
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[DATASTORE_KEY_TOKEN] = token
        }
    }

    override suspend fun loadToken(): String? {
        return dataStore.data.map { preferences ->
            preferences[DATASTORE_KEY_TOKEN]
        }.firstOrNull()
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(DATASTORE_KEY_TOKEN)
        }
    }

    override suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[DATASTORE_KEY_NAME] = name
        }
    }

    override suspend fun saveUserEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[DATASTORE_KEY_EMAIL] = email
        }
    }

    override suspend fun loadUserName(): String? {
        return dataStore.data.map { preferences ->
            preferences[DATASTORE_KEY_NAME]
        }.firstOrNull()
    }

    override suspend fun loadUserEmail(): String? {
        return dataStore.data.map { preferences ->
            preferences[DATASTORE_KEY_EMAIL]
        }.firstOrNull()
    }

}
