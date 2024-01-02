package com.example.showsatellitedata.data.repository

import com.example.showsatellitedata.data.room.dao.SatelliteDao
import com.example.showsatellitedata.domain.repository.SatelliteRepository
import com.example.showsatellitedata.entity.SatelliteModel
import com.example.showsatellitedata.entity.SatelliteDetailModel

class SatelliteRepositoryImpl(
    private val satelliteDao: SatelliteDao
): SatelliteRepository {

    override suspend fun getSatelliteDetailById(id: Int): SatelliteDetailModel? =
        satelliteDao.getSatelliteDetailById(id)

    override suspend fun insertSatelliteDetails(satelliteDetails: List<SatelliteDetailModel>) =
        satelliteDao.insertSatelliteDetail(satelliteDetails)

}