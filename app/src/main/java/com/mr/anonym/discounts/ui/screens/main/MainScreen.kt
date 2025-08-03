package com.mr.anonym.discounts.ui.screens.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mr.anonym.data.local.instance.SharedPreferencesInstance

@Composable
fun MainScreen(
    navController: NavController
) {

//    Contexts
    val context = LocalContext.current

//    Objects
    val sharedPreferences = SharedPreferencesInstance(context)

//    Booleans
    val systemThemeState = sharedPreferences.systemThemeState()
    val darkThemeState = sharedPreferences.darkThemeState()

//    Colors
    val componentColor = Color(67, 123, 205, 255)
    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        systemThemeState -> systemPrimaryColor
        darkThemeState -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if ( isSystemInDarkTheme() ) Color.White else Color.Black
    val secondaryColor = when {
        systemThemeState -> systemSecondaryColor
        darkThemeState -> Color.White
        else -> Color.Black
    }
    val systemItemsColor = if ( isSystemInDarkTheme() ) Color(16, 15, 15, 255) else Color.White
    val itemsColor = when {
        systemThemeState -> systemItemsColor
        darkThemeState -> Color(16, 15, 15, 255)
        else -> Color.White
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    val context = LocalContext.current
    MainScreen(
        navController = NavController(context)
    )
}