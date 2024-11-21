package com.sankyawhtwe.codetest.di

import android.util.Log.v
import com.sankyawhtwe.codetest.ui.viewmodel.CreateProductViewModel
import com.sankyawhtwe.codetest.ui.viewmodel.LoginViewModel
import com.sankyawhtwe.codetest.ui.viewmodel.ProductDetailsViewModel
import com.sankyawhtwe.codetest.ui.viewmodel.ProductViewModel
import com.sankyawhtwe.codetest.ui.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val productViewModelModule = module {
    viewModelOf(::ProductViewModel)
    viewModelOf(::ProductDetailsViewModel)
    viewModelOf(::CreateProductViewModel)
    viewModelOf (::LoginViewModel)
    viewModelOf(::SignUpViewModel)
}