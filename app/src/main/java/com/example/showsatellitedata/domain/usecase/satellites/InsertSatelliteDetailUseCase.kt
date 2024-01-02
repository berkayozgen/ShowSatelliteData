package com.example.showsatellitedata.domain.usecase.satellites


import com.example.showsatellitedata.base.BaseUseCase
import com.example.showsatellitedata.domain.repository.SatelliteRepository
import com.example.showsatellitedata.entity.SatelliteDetailModel

class InsertSatelliteDetailUseCase(
    private val satelliteRepository: SatelliteRepository
): BaseUseCase<SatelliteDetailModel, Unit>() {
    override suspend fun invoke(param: SatelliteDetailModel) = performUseCase {
        satelliteRepository.insertSatelliteDetail(param)
    }
}