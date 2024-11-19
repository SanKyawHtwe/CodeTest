package com.sankyawhtwe.codetest.di

import com.sankyawhtwe.codetest.data.repository.ProductRepository
import com.sankyawhtwe.codetest.data.repository.ProductRepositoryImpl
import org.koin.dsl.module

val productRepositoryModule = module {
    single {
        ProductRepositoryImpl(
            productRemoteDataSource = get()
        ) as ProductRepository
    }
}