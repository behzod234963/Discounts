package com.mr.anonym.discounts.di.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DiscountsApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}