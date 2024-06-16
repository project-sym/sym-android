package com.ilharper.sym.app

import android.app.Application
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SymApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
