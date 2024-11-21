package com.sankyawhtwe.codetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sankyawhtwe.codetest.data.repository.AuthRepository

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
}