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

class SatellitesDetailFragment : BaseFragment<SatellitesDetailViewModel, FragmentSatellitesDetailBinding>() {

    override fun getViewModel(): Lazy<SatellitesDetailViewModel> = viewModel()

    override fun getLayout(): Int = R.layout.fragment_satellites_detail

    override fun onViewCreateFinished() {

    }

}