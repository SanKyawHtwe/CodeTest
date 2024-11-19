package com.sankyawhtwe.codetest.di

import com.sankyawhtwe.codetest.data.datasource.ProductRemoteDataSource
import com.sankyawhtwe.codetest.data.service.KtorUtils
import org.koin.dsl.module

val networkModule = module {
    single {
        KtorUtils().createKtor()
    }
}