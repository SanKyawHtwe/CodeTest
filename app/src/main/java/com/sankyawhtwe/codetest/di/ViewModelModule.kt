package com.sankyawhtwe.codetest.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.sankyawhtwe.codetest.ui.viewmodel.ProductDetailsViewModel
import com.sankyawhtwe.codetest.ui.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val productViewModelModule = module {
    viewModelOf(::ProductViewModel)
    viewModelOf(::ProductDetailsViewModel)
}