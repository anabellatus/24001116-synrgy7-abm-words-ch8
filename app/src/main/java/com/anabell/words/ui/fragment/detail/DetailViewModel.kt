package com.anabell.words.ui.fragment.detail

import android.content.Context
import android.util.Log
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
import com.anabell.words.domain.GadgetRepository
import com.anabell.words.data.model.Gadget
import com.anabell.words.data.repository.GadgetRepositoryImpl
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: GadgetRepository,
) : ViewModel() {

//    private val _error = MutableLiveData<Throwable>()
//    val error: LiveData<Throwable> = _error
//
//    private val _gadgetLocal = MutableLiveData<List<Gadget>>()
//    val gadgetLocal: LiveData<List<Gadget>> = _gadgetLocal

    private val _gadgets: MutableLiveData<List<Gadget>> = MutableLiveData()
    val gadgets: LiveData<List<Gadget>> = _gadgets

    private var name: String? = null

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
                    return DetailViewModel(
                        repository = repository,
                    ) as T
                }
            }
    }

    //unused
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

//    fun addGadgetToFavorites(
//        image: String,
//        name: String,
//        category: String,
//        price: Int,
//        id: Int = -1,
//    ) {
//        viewModelScope.launch {
//            val gadget = Gadget(
//                id = if (id == -1) 0 else id,
//                image = image,
//                name = name,
//                category = category,
//                price = price
//            )
//            repository.addFavorite(gadget)
//            gadget.isFavorite = true
//            _gadgetLocal.value = repository.loadGadget()
//        }
//    }
//
//    fun removeGadgetFromFavorites(gadget: Gadget) {
//        Log.d("DetailFragment", "onRemoveFromFavorite: $gadget")
//        viewModelScope.launch {
//            repository.deleteGadget(gadget)
//            gadget.isFavorite = false
//            _gadgetLocal.value = repository.loadGadget()
//        }
//    }

//    fun loadGadgetsFromFavorite() {
//        viewModelScope.launch {
//            try {
//                _gadgetLocal.value = repository.loadGadget()
//            } catch (throwable: Throwable) {
//                _error.value = throwable
//            }
//        }
//    }
}