package com.anabell.words.ui.fragment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anabell.words.domain.model.CategoryGadget
import com.anabell.words.domain.repository.GadgetRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val gadgetRepository: GadgetRepository,
) : ViewModel() {

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _categories: MutableLiveData<List<CategoryGadget>> = MutableLiveData()
    val categories: LiveData<List<CategoryGadget>> = _categories

    fun retrieveCategoryData() {
        viewModelScope.launch {
            try {
                _categories.value = gadgetRepository.fetchGadgetCategoryData()
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }
}