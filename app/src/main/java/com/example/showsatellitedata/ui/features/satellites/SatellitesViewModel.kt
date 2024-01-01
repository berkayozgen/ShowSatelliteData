package com.example.showsatellitedata.ui.features.satellites

import com.example.showsatellitedata.base.BaseViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.showsatellitedata.domain.usecase.AppResult
import com.example.showsatellitedata.domain.usecase.GetSatellitesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SatellitesViewModel(
    private val getSatellitesUseCase: GetSatellitesUseCase
): BaseViewModel() {

    init {
        getSatellites()
    }

    fun getSatellites() {
        viewModelScope.launch {
            getSatellitesUseCase(Unit).collect { result ->
                when (result) {
                    is AppResult.Error -> {}
                    is AppResult.Loading -> {}
                    is AppResult.Success -> {
                        Log.d("DataControl", "=> ${result.data}")
                    }
                }
            }
        }
    }
}