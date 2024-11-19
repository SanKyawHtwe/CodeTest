package com.sankyawhtwe.codetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sankyawhtwe.codetest.navigation.AppNavHost
import com.sankyawhtwe.codetest.ui.theme.CodeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeTestTheme {
                AppNavHost()
            }
        }
    }
}

