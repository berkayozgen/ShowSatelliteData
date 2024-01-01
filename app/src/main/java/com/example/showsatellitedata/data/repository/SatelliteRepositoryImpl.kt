package com.example.showsatellitedata.data.repository

import com.example.showsatellitedata.data.room.dao.SatelliteDao
import com.example.showsatellitedata.domain.repository.SatelliteRepository
import com.example.showsatellitedata.entity.SatelliteModel

class SatelliteRepositoryImpl(
    private val satelliteDao: SatelliteDao
): SatelliteRepository {

    override suspend fun getSatellites(): List<SatelliteModel> = satelliteDao.getSatellites()

    override suspend fun getSatelliteById(id: Int): SatelliteModel? =
        satelliteDao.getSatelliteById(id)

    override suspend fun cacheSatellites(list: List<SatelliteModel>) =
        satelliteDao.insertSatellites(list)

}