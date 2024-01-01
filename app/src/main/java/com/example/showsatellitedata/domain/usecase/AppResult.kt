package com.example.showsatellitedata.domain.usecase

sealed interface AppResult <T> {
    data class Loading<T>(val isLoading: Boolean): AppResult<T>

    data class Success<T>(val data: T?): AppResult<T>

    data class Error<T>(val message: String?): AppResult<T>
}