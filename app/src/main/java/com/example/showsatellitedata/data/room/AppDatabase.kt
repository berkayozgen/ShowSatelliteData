package com.example.showsatellitedata.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.showsatellitedata.data.room.dao.SatelliteDao
import com.example.showsatellitedata.entity.SatelliteModel

@Database(entities = [SatelliteModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun satelliteDao(): SatelliteDao

}