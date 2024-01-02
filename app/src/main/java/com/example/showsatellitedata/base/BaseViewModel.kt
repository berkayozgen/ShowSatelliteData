package com.example.showsatellitedata.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

open class BaseViewModel() : ViewModel(), KoinComponent {

    val showLoading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<Any?>()

    fun showProgress(show: Boolean) {
        showLoading.value = show
    }

    fun onErrorShowed() {
        onError.value = null
    }

    fun showError(error: Exception) {
        onError.postValue(error)
    }

}