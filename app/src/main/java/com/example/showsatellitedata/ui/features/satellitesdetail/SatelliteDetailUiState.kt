package com.example.showsatellitedata.ui.features.satellitesdetail

import com.example.showsatellitedata.entity.SatelliteDetailModel
import com.example.showsatellitedata.entity.SatelliteModel

data class SatelliteDetailUiState(
    val loadFromAsset: Boolean? = null,
    val detail: SatelliteDetailModel? = null,
)