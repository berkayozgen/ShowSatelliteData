package com.example.showsatellitedata.domain.repository

import com.example.showsatellitedata.entity.SatelliteModel
import com.example.showsatellitedata.entity.SatelliteDetailModel

interface SatelliteRepository {

    suspend fun getSatellites(): List<SatelliteModel>

    suspend fun getSatelliteDetailById(id: Int): SatelliteDetailModel?

    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailModel)

}