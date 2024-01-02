package com.example.showsatellitedata.ui.features.satellites

import com.example.showsatellitedata.R
import com.example.showsatellitedata.base.BaseFragment
import com.example.showsatellitedata.databinding.FragmentSatellitesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.lifecycleScope
import com.example.showsatellitedata.entity.SatelliteModel
import com.example.showsatellitedata.utils.assets.loadJson
import kotlinx.coroutines.launch
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged

class SatellitesFragment : BaseFragment<SatellitesViewModel, FragmentSatellitesBinding>() {

    private val satellitesAdapter: SatellitesRecyclerAdapter = SatellitesRecyclerAdapter { satellite ->
        findNavController().navigate(R.id.satellitesDetailFragment,
            args = bundleOf("satellite" to satellite)
        )
    }

    override fun getViewModel(): Lazy<SatellitesViewModel> = viewModel()

    override fun getLayout(): Int = R.layout.fragment_satellites

    override fun onViewCreateFinished() {
        initUi()
        initObservers()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.showProgress(true)
            requireActivity().loadJson<Array<SatelliteModel>>("SATELLITE-LIST.json")?.let {satellites ->
                viewModel.initializeUi(satellites.toList())
            }
            viewModel.showProgress(false)
        }

    }

    private fun initUi() {
        binding?.apply {
            rvSatellites.adapter = satellitesAdapter
            etSearch.doAfterTextChanged { text ->
                viewModel.onSearch(text.toString())
            }
        }
    }

    private fun initObservers() {
        viewModel.satellites.observe(viewLifecycleOwner) { list ->
            updateSatellites(list ?: emptyList())
        }
    }

    private fun updateSatellites(list: List<SatelliteModel>) {
        satellitesAdapter.submitList(list)
    }


}