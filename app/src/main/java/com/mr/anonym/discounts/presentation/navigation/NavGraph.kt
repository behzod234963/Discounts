package com.mr.anonym.discounts.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mr.anonym.data.local.instance.SharedPreferencesInstance
import com.mr.anonym.discounts.ui.screens.informationScreen.InformationScreen
import com.mr.anonym.discounts.ui.screens.locationScreen.LocationScreen
import com.mr.anonym.discounts.ui.screens.mainScreen.MainScreen
import com.mr.anonym.discounts.ui.screens.onBoardingScreen.OnBoardingScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavGraph() {

//    Context
    val context = LocalContext.current

//    Objects
    val sharedPreferences = SharedPreferencesInstance(context)

//    Booleans
    val firstLaunchState = sharedPreferences.firstLaunchState()

//    State
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = when {
            firstLaunchState -> ScreensRouter.OnBoardingScreen.route
            else -> ScreensRouter.MainScreen.route
        }
    ){
        composable(ScreensRouter.OnBoardingScreen.route) {
            OnBoardingScreen(navController)
        }
        composable (ScreensRouter.InformationScreen.route ){
            InformationScreen(navController)
        }
        composable (ScreensRouter.LocationScreen.route){
            LocationScreen(navController)
        }
        composable (ScreensRouter.MainScreen.route ){
            MainScreen(navController)
        }
    }
}