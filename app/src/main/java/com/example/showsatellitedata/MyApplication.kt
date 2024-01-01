package com.example.showsatellitedata

import android.app.Application
import com.example.showsatellitedata.ui.features.main.mainModule
import com.example.showsatellitedata.ui.features.satellites.satellitesModule
import com.example.showsatellitedata.ui.features.satellitesdetail.satellitesDetailModule
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                mainModule,
                satellitesModule,
                satellitesDetailModule
            )
        }
    }
}