package com.example.assignment1
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi

import com.example.assignment1.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class PetsApplication : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PetsApplication)
            modules(appModules)
        }
    }
}
