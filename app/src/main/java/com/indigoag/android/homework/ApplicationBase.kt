package com.indigoag.android.homework

import android.app.Application
import com.example.usecases.di.useCaseModule
import com.indigoag.android.homework.di.viewModelModule
import com.indigoag.repository.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ApplicationBase : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ApplicationBase)
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    networkModule
                )
            )
        }
    }
}