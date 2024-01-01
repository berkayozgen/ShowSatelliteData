package com.example.showsatellitedata.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.showsatellitedata.entity.SatelliteModel

@Dao
interface SatelliteDao {

    @Insert
    suspend fun insertSatellites(list: List<SatelliteModel>)

    @Query("SELECT * FROM Satellites")
    suspend fun getSatellites(): List<SatelliteModel>

    @Query("SELECT * FROM Satellites WHERE :id == id")
    suspend fun getSatelliteById(id: Int): SatelliteModel?

}