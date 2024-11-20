package com.sankyawhtwe.codetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sankyawhtwe.codetest.ui.screens.HomeRoute
import com.sankyawhtwe.codetest.ui.screens.LoginRoute
import com.sankyawhtwe.codetest.ui.screens.createProductScreen
import com.sankyawhtwe.codetest.ui.screens.homeScreen
import com.sankyawhtwe.codetest.ui.screens.loginScreen
import com.sankyawhtwe.codetest.ui.screens.navigateToCreateProductScreen
import com.sankyawhtwe.codetest.ui.screens.navigateToDetailsScreen
import com.sankyawhtwe.codetest.ui.screens.navigateToHomeScreen
import com.sankyawhtwe.codetest.ui.screens.productDetailsScreen

@Composable
fun AppNavHost(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        homeScreen(
            onProductClick = {
                navController.navigateToDetailsScreen(it)
            },
            onAddProduct = {
                navController.navigateToCreateProductScreen()
            }
        )
        productDetailsScreen(onNavigateUp = navController::navigateUp)
        createProductScreen()
        loginScreen(
            onLogin = {
                navController.navigateToHomeScreen()
            }
        )
    }
}