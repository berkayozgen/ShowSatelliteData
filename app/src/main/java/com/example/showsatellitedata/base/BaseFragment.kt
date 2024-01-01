package com.example.showsatellitedata.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM: BaseViewModel, DB: ViewDataBinding>: Fragment() {

    protected var binding: DB? = null
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel().value
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayout(),
            container,
            false
        )
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreateFinished()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun getViewModel(): Lazy<VM>
    abstract fun getLayout(): Int
    abstract fun onViewCreateFinished()
}