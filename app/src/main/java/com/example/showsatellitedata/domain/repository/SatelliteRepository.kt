package com.example.showsatellitedata.domain.repository

import com.example.showsatellitedata.entity.SatelliteModel
import com.example.showsatellitedata.entity.SatelliteDetailModel

interface SatelliteRepository {

    suspend fun getSatelliteDetailById(id: Int): SatelliteDetailModel?

    suspend fun insertSatelliteDetails(satelliteDetails: List<SatelliteDetailModel>)

}