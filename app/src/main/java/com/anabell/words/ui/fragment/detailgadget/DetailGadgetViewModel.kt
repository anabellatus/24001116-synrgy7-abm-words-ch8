package com.anabell.words.ui.fragment.detailgadget

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.savedstate.SavedStateRegistryOwner
import com.anabell.words.data.datasource.local.GadgetLocalDataSourceImpl
import com.anabell.words.data.datasource.local.room.RoomDatabase
import com.anabell.words.data.datasource.remote.GadgetRemoteDataSourceImpl
import com.anabell.words.data.model.Gadget
import com.anabell.words.data.repository.GadgetRepositoryImpl
import com.anabell.words.domain.GadgetRepository
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
                    val roomDatabase: RoomDatabase = Room.databaseBuilder(
                        context = context,
                        name = RoomDatabase.NAME,
                        klass = RoomDatabase::class.java,
                    ).build()
                    val repository: GadgetRepository = GadgetRepositoryImpl(
                        localDataSource = GadgetLocalDataSourceImpl(
                            gadgetDao = roomDatabase.gadgetDao()
                        ),
                        remoteDataSource = GadgetRemoteDataSourceImpl()
                    )
                    return DetailGadgetViewModel(
                        repository = repository,
                    ) as T
                }
            }
    }

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