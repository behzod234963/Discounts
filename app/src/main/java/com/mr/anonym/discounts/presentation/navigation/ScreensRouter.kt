package com.mr.anonym.discounts.presentation.navigation

sealed class ScreensRouter(val route: String) {
    object OnBoardingScreen: ScreensRouter("OnBoardingScreen")
    object InformationScreen: ScreensRouter("InformationScreen")
    object MainScreen: ScreensRouter("MainScreen")
}