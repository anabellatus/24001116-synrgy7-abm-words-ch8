package com.anabell.words.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anabell.words.di.Module
import com.anabell.words.ui.auth.AuthCheckerViewModel
import com.anabell.words.ui.auth.login.LoginViewModel
import com.anabell.words.ui.auth.register.RegisterViewModel
import com.anabell.words.ui.fragment.detail.DetailViewModel
import com.anabell.words.ui.fragment.detailgadget.DetailGadgetViewModel
import com.anabell.words.ui.fragment.favorite.FavoriteViewModel
import com.anabell.words.ui.fragment.list.ListViewModel
import com.anabell.words.ui.fragment.profile.ProfileViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val module: Module) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(
                authRepository = module.authRepository,
            ) as T

            RegisterViewModel::class.java -> RegisterViewModel(
                authRepository = module.authRepository,
            ) as T

            DetailGadgetViewModel::class.java -> DetailGadgetViewModel(
                repository = module.gadgetRepository,
            ) as T

            AuthCheckerViewModel::class.java -> AuthCheckerViewModel(
                authRepository = module.authRepository,
            ) as T

            DetailViewModel::class.java -> DetailViewModel(
                repository = module.gadgetRepository,
            ) as T

            FavoriteViewModel::class.java -> FavoriteViewModel(
                gadgetRepository = module.gadgetRepository,
            ) as T

            ListViewModel::class.java -> ListViewModel(
                gadgetRepository = module.gadgetRepository,
            ) as T

//            ProfileViewModel::class.java -> ProfileViewModel(
//                authRepository = module.authRepository,
//
//            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}