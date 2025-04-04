package com.example.assignment1

import android.app.Application
import com.example.assignment1.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Assignment1Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Assignment1Application)
            modules(appModule)
        }
    }
}
