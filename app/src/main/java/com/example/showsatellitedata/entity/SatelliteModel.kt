package com.example.showsatellitedata.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Satellites")
data class SatelliteModel(
    @PrimaryKey val id: Int,
    val active: Boolean,
    val name: String
): Parcelable