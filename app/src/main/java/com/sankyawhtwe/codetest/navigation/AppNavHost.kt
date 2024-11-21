package com.sankyawhtwe.codetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sankyawhtwe.codetest.ui.screens.HomeRoute
import com.sankyawhtwe.codetest.ui.screens.createProductScreen
import com.sankyawhtwe.codetest.ui.screens.homeScreen
import com.sankyawhtwe.codetest.ui.screens.loginScreen
import com.sankyawhtwe.codetest.ui.screens.navigateToCreateProductScreen
import com.sankyawhtwe.codetest.ui.screens.navigateToDetailsScreen
import com.sankyawhtwe.codetest.ui.screens.navigateToHomeScreen
import com.sankyawhtwe.codetest.ui.screens.navigateToSignUpScreen
import com.sankyawhtwe.codetest.ui.screens.productDetailsScreen
import com.sankyawhtwe.codetest.ui.screens.signUpScreen

@Composable
fun AppNavHost(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
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
        createProductScreen(
            onProductCreated = {
                navController.navigateToHomeScreen()
            }
        )
        loginScreen(
            onLoginSuccess = {
                navController.navigateToHomeScreen()
            },
            onSignUpCLick = {
                navController.navigateToSignUpScreen()
            }
        )
        signUpScreen(
            onSignup = {
                navController.navigateToHomeScreen()
            }
        )
    }
}