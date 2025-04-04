package com.example.assignment1
import android.app.Application

import com.example.assignment1.di.appModules
import org.koin.core.context.startKoin

class PetsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}
