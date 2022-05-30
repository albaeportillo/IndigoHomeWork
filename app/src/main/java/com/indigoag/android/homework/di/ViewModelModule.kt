package com.indigoag.android.homework.di

import com.indigoag.android.homework.ui.home.bids.HomeViewModel
import com.indigoag.android.homework.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { HomeViewModel() }
}