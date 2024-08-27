package com.anabell.words.ui.fragment.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anabell.words.domain.model.Gadget
import com.anabell.words.domain.repository.GadgetRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val gadgetRepository: GadgetRepository
) : ViewModel() {

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _gadgets = MutableLiveData<List<Gadget?>>()
    val gadgets: LiveData<List<Gadget?>> = _gadgets

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun loadAllFavoriteGadget() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _gadgets.value = gadgetRepository.loadGadget()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable
            }
        }
    }

}