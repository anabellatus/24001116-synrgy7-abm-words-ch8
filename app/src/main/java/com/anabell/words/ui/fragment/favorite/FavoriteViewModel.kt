package com.anabell.words.ui.fragment.favorite

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
import com.anabell.words.data.datasource.remote.retrofit.provideGadgetService
import com.anabell.words.domain.model.Gadget
import com.anabell.words.data.repository.GadgetRepositoryImpl
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
                        remoteDataSource = GadgetRemoteDataSourceImpl(
                            gadgetService = provideGadgetService()
                        )
                    )
                    return FavoriteViewModel(
                        gadgetRepository = repository,
                    ) as T
                }
            }
    }

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