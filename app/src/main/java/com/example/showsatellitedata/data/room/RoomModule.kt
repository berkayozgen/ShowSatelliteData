package com.example.showsatellitedata.data.room

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DatabaseConstants.name
        ).build()
    }
    factory { get<AppDatabase>().satelliteDao() }
}