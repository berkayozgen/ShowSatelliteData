package com.example.showsatellitedata

import android.app.Application
import com.example.showsatellitedata.ui.features.main.mainModule
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                mainModule,
            )
        }
    }
}