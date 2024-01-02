package com.example.showsatellitedata.ui.features.satellites

import com.example.showsatellitedata.base.BaseViewModel
import androidx.lifecycle.asLiveData
import com.example.showsatellitedata.entity.SatelliteModel
import com.example.showsatellitedata.utils.coroutine.AppDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class SatellitesViewModel(
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val mutableUiState: MutableStateFlow<SatellitesUiState> =
        MutableStateFlow(SatellitesUiState())

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val satellites = mutableUiState.mapLatest { uiState ->
        if (uiState.searchText.isNullOrEmpty()) {
            uiState.allSatellites
        } else {
            uiState.allSatellites?.filter {
                it.name.lowercase().contains(uiState.searchText.lowercase())
            }
        }
    }.flowOn(dispatchers.io)
        .debounce(250L)
        .asLiveData()

    fun initializeUi(list: List<SatelliteModel>) {
        mutableUiState.tryEmit(
            SatellitesUiState(
                allSatellites = list,
            )
        )
    }
    fun onSearch(searchText: String?) {
        mutableUiState.update {
            it.copy(
                searchText = searchText
            )
        }
    }
}