package com.sankyawhtwe.codetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sankyawhtwe.codetest.ui.screens.HomeRoute
import com.sankyawhtwe.codetest.ui.screens.homeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ){
        homeScreen()
    }
}