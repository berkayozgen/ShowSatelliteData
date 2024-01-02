package com.example.showsatellitedata.ui.features.satellitesdetail

import com.example.showsatellitedata.entity.SatelliteDetailModel

data class SatelliteDetailUiState(
    val loadFromAsset: Boolean? = null,
    val detail: SatelliteDetailModel? = null,
)