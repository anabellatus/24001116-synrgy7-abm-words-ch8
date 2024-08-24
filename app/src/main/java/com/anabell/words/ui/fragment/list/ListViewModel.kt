package com.anabell.words.ui.fragment.list

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
import com.anabell.words.data.model.CategoryGadget
import com.anabell.words.data.repository.GadgetRepositoryImpl
import com.anabell.words.domain.GadgetRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val gadgetRepository: GadgetRepository,
) : ViewModel() {

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

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
                    val gadgetRepository: GadgetRepository = GadgetRepositoryImpl(
                        localDataSource = GadgetLocalDataSourceImpl(
                            gadgetDao = roomDatabase.gadgetDao()
                        ),
                        remoteDataSource = GadgetRemoteDataSourceImpl(
                            gadgetService = provideGadgetService()
                        )
                    )
                    return ListViewModel(
                        gadgetRepository = gadgetRepository,
                    ) as T
                }
            }
    }

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