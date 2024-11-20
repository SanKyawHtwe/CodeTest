package com.sankyawhtwe.codetest

import android.app.Application
import com.sankyawhtwe.codetest.di.networkModule
import com.sankyawhtwe.codetest.di.productRemoteDataSourceModule
import com.sankyawhtwe.codetest.di.productRepositoryModule
import com.sankyawhtwe.codetest.di.productViewModelModule
import org.koin.core.context.startKoin

class ProductApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(
                productViewModelModule,
                productRepositoryModule,
                productRemoteDataSourceModule,
                networkModule
            )
        }
    }
}