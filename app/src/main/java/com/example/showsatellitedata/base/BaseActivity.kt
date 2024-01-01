package com.example.showsatellitedata.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : FragmentActivity() {
    protected var binding: DB? = null
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        viewModel = getViewModel().value

        //binding.setVariable(BR.viewModel)
        binding?.lifecycleOwner = this
        onCreateFinished()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    abstract fun onCreateFinished()
    abstract fun getLayout(): Int
    abstract fun getViewModel(): Lazy<VM>
}