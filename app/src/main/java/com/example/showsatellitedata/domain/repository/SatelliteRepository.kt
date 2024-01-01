package com.example.showsatellitedata.domain.repository

import com.example.showsatellitedata.entity.SatelliteModel

interface SatelliteRepository {

    suspend fun getSatellites(): List<SatelliteModel>

    suspend fun getSatelliteById(id: Int): SatelliteModel?

    suspend fun cacheSatellites(list: List<SatelliteModel>)

}