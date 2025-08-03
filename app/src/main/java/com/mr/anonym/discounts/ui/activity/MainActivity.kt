package com.mr.anonym.discounts.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.mr.anonym.data.local.instance.SharedPreferencesInstance
import com.mr.anonym.discounts.presentation.navigation.NavGraph
import com.mr.anonym.discounts.ui.theme.DiscountsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var sharedPrefs : SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val systemThemeState = sharedPrefs.systemThemeState()
            val darkThemeState = sharedPrefs.darkThemeState()
            DiscountsTheme (
                darkTheme = when{
                    systemThemeState -> isSystemInDarkTheme()
                    darkThemeState -> true
                    else -> false
                }
            ){
                NavGraph()
            }
        }
    }
}