package com.anabell.words.ui.auth

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.anabell.words.data.datasource.local.AuthLocalDataSourceImpl
import com.anabell.words.data.datasource.local.SharedPreferencesFactory
import com.anabell.words.data.datasource.local.dataStore
import com.anabell.words.data.datasource.remote.AuthRemoteDataSourceImpl
import com.anabell.words.data.repository.AuthRepositoryImpl
import com.anabell.words.domain.AuthRepository
import kotlinx.coroutines.launch

class AuthCheckerViewModel(
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
                    return AuthCheckerViewModel(
                        authRepository = authRepository
                    ) as T
                }
            }
    }

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun checkLogin() {
        viewModelScope.launch {
            try {
                _isLoggedIn.value = !authRepository.loadToken().isNullOrEmpty()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }

        }

    }
}