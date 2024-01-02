package com.example.showsatellitedata.ui.features.satellitesdetail

import com.example.showsatellitedata.base.BaseViewModel
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.showsatellitedata.domain.usecase.AppResult
import com.example.showsatellitedata.domain.usecase.satellites.GetSatelliteByIdUseCase
import com.example.showsatellitedata.entity.SatelliteDetailModel
import com.example.showsatellitedata.entity.SatelliteModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch


class SatellitesDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getSatelliteByIdUseCase: GetSatelliteByIdUseCase
): BaseViewModel() {

    private val mutableUiState: MutableSharedFlow<SatelliteDetailUiState> = MutableSharedFlow()
    val uiState = mutableUiState.shareIn(viewModelScope, SharingStarted.WhileSubscribed(5_000))

    private var satellite: SatelliteModel? = null

    init {
        savedStateHandle.get<SatelliteModel?>("satellite")?.let {
            satellite = it
            getSatelliteDetail(it)
        }
    }

    fun getSatelliteDetail(satelliteModel: SatelliteModel) {
        viewModelScope.launch {
            getSatelliteByIdUseCase(satelliteModel.id).collect { result ->
                when(result) {
                    is AppResult.Error -> {}
                    is AppResult.Loading -> showProgress(result.isLoading)
                    is AppResult.Success -> {
                        mutableUiState.tryEmit(SatelliteDetailUiState(detail = result.data, loadFromAsset = result.data == null))
                    }
                }
            }
        }
    }

    fun insertSatelliteDetails(list: List<SatelliteDetailModel>) {
        viewModelScope.launch {

        }
    }

}