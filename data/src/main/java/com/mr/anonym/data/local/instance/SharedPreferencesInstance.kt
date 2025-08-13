package com.mr.anonym.data.local.instance

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesInstance(private val context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)

    fun isSystemTheme(state: Boolean){
        sharedPreferences.edit{ putBoolean("systemThemeState",state) }
    }
    fun systemThemeState(): Boolean =
        sharedPreferences.getBoolean("systemThemeState",true)

    fun isDarkTheme(state: Boolean){
        sharedPreferences.edit { putBoolean("DarkTheme",state) }
    }
    fun darkThemeState(): Boolean =
        sharedPreferences.getBoolean("DarkTheme",true)

    fun isFirstLaunch(state: Boolean){
        sharedPreferences.edit{ putBoolean( "FirstLaunch" , state ) }
    }
    fun firstLaunchState(): Boolean =
        sharedPreferences.getBoolean("FirstLaunch",true)


    fun showLocation(state: Boolean){
        sharedPreferences.edit{ putBoolean( "showLocation" , state ) }
    }
    fun locationVisibilityState(): Boolean =
        sharedPreferences.getBoolean( "showLocation" , true )
}