package com.example.showsatellitedata.domain.usecase

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetSatellitesUseCase)
}