package com.anabell.words.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anabell.words.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val token = authRepository.register(name, email, password)
                authRepository.saveToken(token)
                authRepository.saveUserName(name)
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