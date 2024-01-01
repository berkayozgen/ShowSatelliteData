package com.example.showsatellitedata

import android.app.Application
import com.example.showsatellitedata.ui.features.main.mainModule
import com.example.showsatellitedata.ui.features.satellites.satellitesModule
import com.example.showsatellitedata.ui.features.satellitesdetail.satellitesDetailModule
import com.example.showsatellitedata.data.room.roomModule
import com.example.showsatellitedata.di.appModule
import com.example.showsatellitedata.di.repositoryModule
import com.example.showsatellitedata.domain.usecase.useCaseModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                appModule,
                roomModule,
                repositoryModule,
                useCaseModule,
                mainModule,
                satellitesModule,
                satellitesDetailModule
            )
        }
    }
}