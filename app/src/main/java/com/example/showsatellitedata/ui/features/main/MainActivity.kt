package com.example.showsatellitedata.ui.features.main

import androidx.navigation.fragment.NavHostFragment
import com.example.showsatellitedata.R
import com.example.showsatellitedata.base.BaseActivity
import com.example.showsatellitedata.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun onCreateFinished() {
        binding?.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController

        }
    }

    override fun getLayout(): Int = R.layout.activity_main

    override fun getViewModel(): Lazy<MainViewModel> = viewModel()

}