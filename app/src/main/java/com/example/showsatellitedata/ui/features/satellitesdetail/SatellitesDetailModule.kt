package com.example.showsatellitedata.ui.features.satellitesdetail

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val satellitesDetailModule = module {
    viewModelOf(::SatellitesDetailViewModel)
}