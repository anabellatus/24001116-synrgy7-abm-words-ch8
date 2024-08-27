package com.anabell.words.ui.fragment.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anabell.words.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    fun getProfileData() {
        viewModelScope.launch {
            try {
//                _userName.value = authRepository.loadUserName()
                _userEmail.value = authRepository.loadUserEmail()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }

    }

    fun logout() {
        viewModelScope.launch {
            try {
                authRepository.clearToken()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }

    }
}