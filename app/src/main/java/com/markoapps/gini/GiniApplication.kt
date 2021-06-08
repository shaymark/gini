package com.markoapps.gini

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GiniApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        Log.d("create", "onCreate: create")
    }
}