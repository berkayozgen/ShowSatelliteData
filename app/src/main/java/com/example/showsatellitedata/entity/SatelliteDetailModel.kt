package com.example.showsatellitedata.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "SatelliteDetails")
data class SatelliteDetailModel(
    @PrimaryKey val id: Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int,
    @SerializedName("first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Int
): Parcelable