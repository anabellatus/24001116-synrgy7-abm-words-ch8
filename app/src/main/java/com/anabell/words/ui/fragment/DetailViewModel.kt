package com.anabell.words.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anabell.words.data.GadgetDataSource
import com.anabell.words.domain.GadgetRepository
import com.anabell.words.ui.gadgetrecycler.Gadget

class DetailViewModel(
    private val repository: GadgetRepository,
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository: GadgetRepository = GadgetDataSource()
                DetailViewModel(repository = myRepository)
            }
        }
    }

    private val _gadgets: MutableLiveData<List<Gadget>> = MutableLiveData()
    val gadgets: LiveData<List<Gadget>> = _gadgets

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
        _gadgets.value = repository.fetchData()
    }
}