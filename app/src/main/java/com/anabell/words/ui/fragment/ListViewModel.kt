package com.anabell.words.ui.fragment

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.anabell.words.data.AuthRepositoryImpl
import com.anabell.words.data.datasource.local.AuthLocalDataSourceImpl
import com.anabell.words.data.datasource.local.SharedPreferencesFactory
import com.anabell.words.data.datasource.remote.AuthRemoteDataSourceImpl
import com.anabell.words.domain.AuthRepository
import com.anabell.words.ui.categorygadgetrecyclerview.CategoryGadget

class ListViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

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
                    val authRepository: AuthRepository = AuthRepositoryImpl(
                        authLocalDataSource = AuthLocalDataSourceImpl(
                            sharedPreferences = SharedPreferencesFactory().createSharedPreferences(
                                context
                            )
                        ),
                        authRemoteDataSource = AuthRemoteDataSourceImpl(),
                    )
                    return ListViewModel(
                        authRepository = authRepository
                    ) as T
                }
            }
    }

    private val _categories: MutableLiveData<List<CategoryGadget>> = MutableLiveData()
    val categories: LiveData<List<CategoryGadget>> = _categories

    fun retrieveCategoryData() {
        _categories.value = listOf(
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/3437/3437364.png",
                name = "Smartphone",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/2888/2888704.png",
                name = "Laptop",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/564/564579.png",
                name = "Tablet",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/5906/5906114.png",
                name = "Earphone",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/2668/2668914.png",
                name = "Camera",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/8462/8462356.png",
                name = "Speaker",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/617/617694.png",
                name = "Smartwatch",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/771/771261.png",
                name = "Game Console",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/5606/5606227.png",
                name = "TV",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/6212/6212141.png",
                name = "Tools",
            ),
        )
    }
}