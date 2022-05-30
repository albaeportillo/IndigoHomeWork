package com.example.usecases.di

import com.example.usecases.usecase.BidsUseCase
import com.example.usecases.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { LoginUseCase() }
    single { BidsUseCase() }
}
