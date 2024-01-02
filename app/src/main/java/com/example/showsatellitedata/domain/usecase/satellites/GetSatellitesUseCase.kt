package com.example.showsatellitedata.domain.usecase.satellites

import com.example.showsatellitedata.base.BaseUseCase
import com.example.showsatellitedata.domain.repository.SatelliteRepository
import com.example.showsatellitedata.entity.SatelliteModel

class GetSatellitesUseCase(
    private val satelliteRepository: SatelliteRepository
): BaseUseCase<Unit, List<SatelliteModel>>() {
    override suspend operator fun invoke(param: Unit) = performUseCase {
        satelliteRepository.getSatellites()
    }
}