package com.example.showsatellitedata.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

open class BaseViewModel() : ViewModel(), KoinComponent {

    val showLoading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<String?>()

    fun showProgress(show: Boolean) {
        showLoading.value = show
    }

    fun onErrorShowed() {
        onError.value = null
    }

    fun showError(error: String?) {
        onError.postValue(error)
    }

}