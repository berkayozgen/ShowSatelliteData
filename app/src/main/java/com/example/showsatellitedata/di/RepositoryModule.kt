package com.example.showsatellitedata.di

import com.example.showsatellitedata.data.repository.SatelliteRepositoryImpl
import com.example.showsatellitedata.domain.repository.SatelliteRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<SatelliteRepository> { SatelliteRepositoryImpl(get()) }
}