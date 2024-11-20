package com.sankyawhtwe.codetest.di

import com.sankyawhtwe.codetest.ui.viewmodel.CreateProductViewModel
import com.sankyawhtwe.codetest.ui.viewmodel.ProductDetailsViewModel
import com.sankyawhtwe.codetest.ui.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val productViewModelModule = module {
    viewModelOf(::ProductViewModel)
    viewModelOf(::ProductDetailsViewModel)
    viewModelOf(::CreateProductViewModel)
}