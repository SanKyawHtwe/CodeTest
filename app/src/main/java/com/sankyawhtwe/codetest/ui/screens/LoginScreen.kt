package com.sankyawhtwe.codetest.ui.screens

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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sankyawhtwe.codetest.ui.theme.CodeTestTheme
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

fun NavGraphBuilder.loginScreen(onLogin: () -> Unit) {
    composable<LoginRoute> {
        LoginScreen(
            onLogin = onLogin
        )
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit
) {
    Scaffold { contentPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var email by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
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
                    onClick = onLogin
                ) {
                    Text("Login")
                }
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                ) {
                    Text(text = "Don't have an account?")
                    Spacer(Modifier.size(8.dp))
                    Text(text = "Sign Up")
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CodeTestTheme {
        LoginScreen(
            onLogin = {}
        )

    }
}