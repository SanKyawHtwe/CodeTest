package com.sankyawhtwe.codetest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sankyawhtwe.codetest.ui.theme.CodeTestTheme
import com.sankyawhtwe.codetest.ui.viewmodel.LoginUiState
import com.sankyawhtwe.codetest.ui.viewmodel.LoginViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object LoginRoute

fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit,
    onSignUpCLick: () -> Unit
) {
    composable<LoginRoute> {
        val viewModel: LoginViewModel = koinViewModel()
        val uiState = viewModel.loginUiState.collectAsStateWithLifecycle()
        LoginScreen(
            onLogin = viewModel::logIn,
            onSignUpCLick = onSignUpCLick,
            uiState = uiState.value,
            onLoginSuccess = onLoginSuccess
        )
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onLoginSuccess: () -> Unit,
    onLogin: (String, String) -> Unit,
    onSignUpCLick: () -> Unit
) {
    if (uiState is LoginUiState.Success) {
        onLoginSuccess()
    }
    Scaffold { contentPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = email,
                    maxLines = 1,
                    label = { Text("Email") },
                    singleLine = true,
                    onValueChange = {
                        email = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = password,
                    maxLines = 1,
                    label = { Text("Password") },
                    singleLine = true,
                    onValueChange = {
                        password = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    )
                )
                Spacer(modifier = Modifier.size(16.dp))

            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    onClick = {
                        onLogin(
                            email, password
                        )
                    }
                ) {
                    Text("Login")
                }
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                ) {
                    Text(text = "Don't have an account?")
                    Spacer(Modifier.size(8.dp))
                    Text(
                        modifier = Modifier.clickable(
                            onClick = onSignUpCLick
                        ),
                        text = "Sign Up",
                        color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun Preview() {
//    CodeTestTheme {
//        LoginScreen(
//            onLogin = {},
//            onSignUpCLick = {}
//        )
//
//    }
//}