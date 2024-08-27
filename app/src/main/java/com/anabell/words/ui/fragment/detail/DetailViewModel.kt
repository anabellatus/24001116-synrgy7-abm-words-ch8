package com.anabell.words.ui.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anabell.words.domain.model.Gadget
import com.anabell.words.domain.repository.GadgetRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: GadgetRepository,
) : ViewModel() {

    private val _gadgets: MutableLiveData<List<Gadget>> = MutableLiveData()
    val gadgets: LiveData<List<Gadget>> = _gadgets

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var name: String? = null

    fun getUrl(name: String): String {
        return "https://www.google.com/search?q=$name"
    }

    fun filterGadgetByCategory(gadgets: List<Gadget>, categoryName: String): List<Gadget> {
        return gadgets.filter { it.category == categoryName }
    }

    fun setCategoryName(categoryName: String) {
        name = categoryName
    }

    fun retrieveGadgetData() {
        viewModelScope.launch {
            try {
                _gadgets.value = repository.fetchGadgetData()
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }
}