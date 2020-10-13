package com.dartharrmi.weathery.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication

/**
 * Base [Application] for the current app, initializes the dependency injection.
 */
class BaseApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}