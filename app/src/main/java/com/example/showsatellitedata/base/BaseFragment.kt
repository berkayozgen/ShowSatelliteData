package com.example.showsatellitedata.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import android.app.AlertDialog

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
        initializeProgressListener()
        onViewCreateFinished()
    }

    private fun initializeProgressListener() {
        viewModel.showLoading.observe(viewLifecycleOwner) { showLoading ->
            //TODO: Dialog Fragment -> Progress indicator
            /*if (showLoading)
                 progressDialog.show(childFragmentManager)
             else
                 progreesDialog.dismiss()*/
        }

        viewModel.onError.observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage("$it")
                .setPositiveButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun getViewModel(): Lazy<VM>
    abstract fun getLayout(): Int
    abstract fun onViewCreateFinished()
}