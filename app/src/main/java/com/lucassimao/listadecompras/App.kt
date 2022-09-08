package com.lucassimao.listadecompras

import android.app.Application
import com.lucassimao.listadecompras.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            databaseModule
        }
    }
}