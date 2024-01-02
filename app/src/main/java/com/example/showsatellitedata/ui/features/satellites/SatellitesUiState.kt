package com.example.showsatellitedata.ui.features.satellites

import com.example.showsatellitedata.entity.SatelliteModel

data class SatellitesUiState(
    val allSatellites: List<SatelliteModel>? = null,
    val searchText: String? = null
)