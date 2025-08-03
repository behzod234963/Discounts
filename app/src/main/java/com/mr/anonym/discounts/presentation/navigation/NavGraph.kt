package com.mr.anonym.discounts.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mr.anonym.discounts.ui.screens.onBoarding.OnBoardingScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreensRouter.OnBoardingScreen.route
    ){
        composable(ScreensRouter.OnBoardingScreen.route) {
            OnBoardingScreen(navController)
        }
    }
}