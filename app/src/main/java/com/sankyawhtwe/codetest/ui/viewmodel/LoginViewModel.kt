package com.sankyawhtwe.codetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sankyawhtwe.codetest.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginUiState: MutableStateFlow<LoginUiState> = MutableStateFlow(
        LoginUiState.Idle
    )

    var loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            authRepository.logIn(email = email, password = password).fold(
                {
                    _loginUiState.value = LoginUiState.Success(it)
                },
                {
                    _loginUiState.value = LoginUiState.Error(it.message ?: "Something went wrong")
                }
            )
        }
    }

    fun setIdleState() {
        _loginUiState.value = LoginUiState.Idle
    }

}

sealed class LoginUiState {
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data class Success(val message: String) : LoginUiState()
    data class Error(val error: String) : LoginUiState()

}