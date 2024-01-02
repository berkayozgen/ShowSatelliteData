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
import com.example.showsatellitedata.domain.usecase.satellites.InsertSatelliteDetailUseCase
import com.example.showsatellitedata.entity.SatellitePositionModel
import com.example.showsatellitedata.entity.SatellitePositionResponseModel
import com.example.showsatellitedata.utils.coroutine.AppDispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext

class SatellitesDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getSatelliteByIdUseCase: GetSatelliteByIdUseCase,
    private val insertSatelliteDetailUseCase: InsertSatelliteDetailUseCase,
    private val dispatchers: AppDispatchers
): BaseViewModel() {

    private val mutableUiState: MutableSharedFlow<SatelliteDetailUiState> = MutableSharedFlow()
    val uiState = mutableUiState.shareIn(viewModelScope, SharingStarted.WhileSubscribed(5_000))

    private val _lastPositions: MutableSharedFlow<SatellitePositionModel> = MutableSharedFlow()
    val lastPosition = _lastPositions.transform {
        while (true) {
            emit(it.positions[currentIndex%3])
            currentIndex += 1
            delay(3_000)
        }
    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(5_000))

    private var satellite: SatelliteModel? = null
    private var currentIndex = 0

    init {
        savedStateHandle.get<SatelliteModel?>("satellite")?.let {
            satellite = it
            getSatelliteDetail(it)
        }
    }

    private fun getSatelliteDetail(satelliteModel: SatelliteModel) {
        viewModelScope.launch {
            getSatelliteByIdUseCase(satelliteModel.id).collect { result ->
                when(result) {
                    is AppResult.Error -> {}
                    is AppResult.Loading -> showProgress(result.isLoading)
                    is AppResult.Success -> {
                        mutableUiState.emit(SatelliteDetailUiState(detail = result.data, loadFromAsset = result.data == null))
                    }
                }
            }
        }
    }

    fun insertSatelliteDetails(list: List<SatelliteDetailModel>) {
        viewModelScope.launch {
            insertSatelliteDetailUseCase(list).collect { result ->
                when(result) {
                    is AppResult.Error -> {}
                    is AppResult.Loading -> showProgress(result.isLoading)
                    is AppResult.Success -> {
                        val detail = list.firstOrNull { it.id == satellite?.id }
                        mutableUiState.emit(SatelliteDetailUiState(detail = detail))
                    }
                }
            }
        }
    }
    fun initializeLastPositions(response: SatellitePositionResponseModel) {
        viewModelScope.launch {
            withContext(dispatchers.io) {
                response.list.firstOrNull { it.id == satellite?.id }
            }?.let { position ->
                _lastPositions.emit(position)
            }
        }
    }

    fun getSatellite() = satellite

}