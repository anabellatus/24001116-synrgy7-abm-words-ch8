package com.anabell.words.ui.fragment.detailgadget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anabell.words.domain.model.Gadget
import com.anabell.words.domain.repository.GadgetRepository
import kotlinx.coroutines.launch

class DetailGadgetViewModel(
    private val repository: GadgetRepository,
) : ViewModel() {

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _gadgetLocal = MutableLiveData<Gadget?>()
    val gadgetLocal: LiveData<Gadget?> = _gadgetLocal

    private var name: String? = null

    private val _insertGadget = MutableLiveData<Boolean>()
    val insertGadget: LiveData<Boolean> = _insertGadget

    private val _deleteGadget = MutableLiveData<Boolean>()
    val deleteGadget: LiveData<Boolean> = _deleteGadget


    fun getUrl(name: String): String {
        return "https://www.google.com/search?q=$name"
    }

    fun setCategoryName(categoryName: String) {
        name = categoryName
    }

    fun addGadgetToFavorites(
        image: String,
        name: String,
        category: String,
        price: Int,
        id: Int = -1,
    ) {
        viewModelScope.launch {
            try {
                val gadget = Gadget(
                    id = if (id == -1) 0 else id,
                    image = image,
                    name = name,
                    category = category,
                    price = price
                )
                repository.addFavorite(gadget)
                _insertGadget.value = true
                _isFavorite.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }

    fun removeGadgetFromFavorites() {
        viewModelScope.launch {
            try {
                _gadgetLocal.value?.let { repository.deleteGadget(it) }
                _deleteGadget.value = true
                _isFavorite.value = false
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }

    fun loadGadgetsFromFavorite(id: Int) {
        viewModelScope.launch {
            try {
                val favorited = repository.isFavorite(id)
                _isFavorite.value = favorited
                if (favorited) {
                    _gadgetLocal.value = repository.loadGadgetById(id)
                }
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }
}