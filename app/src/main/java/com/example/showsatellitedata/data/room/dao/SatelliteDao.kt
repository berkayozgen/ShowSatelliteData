package com.example.showsatellitedata.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.showsatellitedata.entity.SatelliteModel
import androidx.room.OnConflictStrategy
import com.example.showsatellitedata.entity.SatelliteDetailModel

@Dao
interface SatelliteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailModel)

    @Query("SELECT * FROM SatelliteDetails WHERE :id == id")
    suspend fun getSatelliteDetailById(id: Int): SatelliteDetailModel?

}