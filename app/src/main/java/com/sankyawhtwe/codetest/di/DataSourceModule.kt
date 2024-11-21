package com.sankyawhtwe.codetest.di

import com.sankyawhtwe.codetest.data.datasource.AuthRemoteDataSource
import com.sankyawhtwe.codetest.data.datasource.AuthRemoteDataSourceImpl
import com.sankyawhtwe.codetest.data.datasource.ProductRemoteDataSource
import com.sankyawhtwe.codetest.data.datasource.ProductRemoteDataSourceImpl
import org.koin.dsl.module

val productRemoteDataSourceModule = module {
    single {
        ProductRemoteDataSourceImpl(
            httpClient = get()
        ) as ProductRemoteDataSource
    }
    single {
        AuthRemoteDataSourceImpl() as AuthRemoteDataSource
    }
}