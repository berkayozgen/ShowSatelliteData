package com.example.showsatellitedata.domain.usecase

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.example.showsatellitedata.domain.usecase.satellites.GetSatelliteByIdUseCase
import com.example.showsatellitedata.domain.usecase.satellites.GetSatellitesUseCase
import com.example.showsatellitedata.domain.usecase.satellites.InsertSatelliteDetailUseCase

val useCaseModule = module {
    factoryOf(::GetSatellitesUseCase)
    factoryOf(::GetSatelliteByIdUseCase)
    factoryOf(::InsertSatelliteDetailUseCase)
}