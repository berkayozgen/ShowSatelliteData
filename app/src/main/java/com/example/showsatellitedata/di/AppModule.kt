package com.example.showsatellitedata.di

import com.example.showsatellitedata.utils.coroutine.AppDispatchers
import com.example.showsatellitedata.utils.coroutine.AppDispatchersImpl
import org.koin.dsl.module

val appModule = module {
    single<AppDispatchers> { AppDispatchersImpl() }
}