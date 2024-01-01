package com.example.showsatellitedata.ui.features.satellites

import com.example.showsatellitedata.R
import com.example.showsatellitedata.base.BaseFragment
import com.example.showsatellitedata.databinding.FragmentSatellitesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController

class SatellitesFragment : BaseFragment<SatellitesViewModel, FragmentSatellitesBinding>() {

    override fun getViewModel(): Lazy<SatellitesViewModel> = viewModel()

    override fun getLayout(): Int = R.layout.fragment_satellites

    override fun onViewCreateFinished() {

    }

}