package com.anabell.words.di.factory

import com.anabell.words.ui.auth.AuthCheckerViewModel
import com.anabell.words.ui.auth.login.LoginViewModel
import com.anabell.words.ui.auth.register.RegisterViewModel
import com.anabell.words.ui.fragment.detail.DetailViewModel
import com.anabell.words.ui.fragment.detailgadget.DetailGadgetViewModel
import com.anabell.words.ui.fragment.favorite.FavoriteViewModel
import com.anabell.words.ui.fragment.list.ListViewModel
import com.anabell.words.ui.fragment.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(authRepository = get()) }

    viewModel { RegisterViewModel(authRepository = get()) }

    viewModel { DetailGadgetViewModel(repository = get()) }

    viewModel { AuthCheckerViewModel(authRepository = get()) }

    viewModel { DetailViewModel(repository = get()) }

    viewModel { FavoriteViewModel(gadgetRepository = get()) }

    viewModel { ListViewModel(gadgetRepository = get()) }

    viewModel { ProfileViewModel(authRepository = get()) }
}