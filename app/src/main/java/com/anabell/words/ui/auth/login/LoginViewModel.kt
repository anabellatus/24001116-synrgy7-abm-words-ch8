package com.anabell.words.ui.auth.login

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.anabell.words.data.datasource.local.AuthLocalDataSourceImpl
import com.anabell.words.data.datasource.local.dataStore
import com.anabell.words.data.datasource.remote.AuthRemoteDataSourceImpl
import com.anabell.words.data.repository.AuthRepositoryImpl
import com.anabell.words.domain.repository.AuthRepository
import kotlinx.coroutines.launch


class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle,
                ): T {
                    val authRepository: AuthRepository = AuthRepositoryImpl(
                        authLocalDataSource = AuthLocalDataSourceImpl(
                            dataStore = context.dataStore,
                        ),
                        authRemoteDataSource = AuthRemoteDataSourceImpl(),
                    )
                    return LoginViewModel(
                        authRepository = authRepository
                    ) as T
                }
            }
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val token = authRepository.login(email, password)
                authRepository.saveToken(token)
                authRepository.saveUserEmail(email)
                _loading.value = false
                _success.value = true
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable
            }
        }
    }

}