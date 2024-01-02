package com.example.showsatellitedata.ui.features.satellitesdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.showsatellitedata.R
import com.example.showsatellitedata.base.BaseFragment
import com.example.showsatellitedata.databinding.FragmentSatellitesBinding
import com.example.showsatellitedata.databinding.FragmentSatellitesDetailBinding
import com.example.showsatellitedata.ui.features.satellites.SatellitesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.showsatellitedata.entity.SatelliteDetailModel
import com.example.showsatellitedata.utils.assets.loadJson
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.example.showsatellitedata.entity.SatellitePositionResponseModel


class SatellitesDetailFragment : BaseFragment<SatellitesDetailViewModel, FragmentSatellitesDetailBinding>() {

    override fun getViewModel(): Lazy<SatellitesDetailViewModel> = viewModel()

    override fun getLayout(): Int = R.layout.fragment_satellites_detail

    override fun onViewCreateFinished() {
        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.CREATED)
            .onEach { uiState ->
                uiState.detail?.let {
                    setDetail(it)
                }
                uiState.loadFromAsset?.let { shouldLoadFromAssets ->
                    if (shouldLoadFromAssets) {
                        loadDataFromAsset()
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.lastPosition
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.CREATED)
            .onEach { positionModel ->
                binding?.tvSatelliteLastPosition?.text =
                    "Last Position: (${positionModel.posX} , ${positionModel.posY})"
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launch {
            loadPositionsFromAsset()
        }
    }

    private fun setDetail(satelliteDetail: SatelliteDetailModel) {
        binding?.apply {
            tvSatelliteName.text = viewModel.getSatellite()?.name
            tvSatelliteFirstFlight.text = satelliteDetail.firstFlight
            tvSatelliteHeightMass.text =
                "Height/Mass: ${satelliteDetail.height}/${satelliteDetail.mass}"
            tvSatelliteCost.text = "Cost: ${satelliteDetail.costPerLaunch}"
        }
    }

    private suspend fun loadDataFromAsset() {
        requireActivity().loadJson<Array<SatelliteDetailModel>>("SATELLITE-DETAIL.json")
            ?.let { details ->
                viewModel.insertSatelliteDetails(details.toList())
            }
    }

    private suspend fun loadPositionsFromAsset() {
        requireActivity().loadJson<SatellitePositionResponseModel>("POSITIONS.json")?.let {
            viewModel.initializeLastPositions(it)
        }
    }

}