package com.example.showsatellitedata.ui.features.satellites

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val satellitesModule = module {
    viewModelOf(::SatellitesViewModel)
}