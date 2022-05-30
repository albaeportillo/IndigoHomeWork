package com.indigoag.repository.network.di

import com.indigoag.repository.network.auth.AuthServiceImpl
import com.indigoag.repository.network.auth.IAuthService
import com.indigoag.repository.network.bids.BidsServiceImpl
import com.indigoag.repository.network.bids.IBidsService
import org.koin.dsl.module


val networkModule = module {
    single<IAuthService> { AuthServiceImpl() }
    single<IBidsService> { BidsServiceImpl() }
}
