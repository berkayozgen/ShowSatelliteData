package com.example.showsatellitedata.domain.usecase.satellites

import com.example.showsatellitedata.base.BaseUseCase
import com.example.showsatellitedata.domain.repository.SatelliteRepository
import com.example.showsatellitedata.entity.SatelliteDetailModel

class GetSatelliteByIdUseCase(
    private val satelliteRepository: SatelliteRepository
): BaseUseCase<Int, SatelliteDetailModel?>() {

    override suspend fun invoke(param: Int) = performUseCase {
        satelliteRepository.getSatelliteDetailById(param)
    }

}