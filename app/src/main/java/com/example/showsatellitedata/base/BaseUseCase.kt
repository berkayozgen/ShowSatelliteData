package com.example.showsatellitedata.base

import com.example.showsatellitedata.domain.usecase.AppResult
import com.example.showsatellitedata.utils.coroutine.AppDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseUseCase<in T, R>: KoinComponent {

    private val dispatchers: AppDispatchers by inject()

    abstract suspend operator fun invoke(param: T): Flow<AppResult<R>>

    suspend fun performUseCase(block: suspend () -> R): Flow<AppResult<R>> = flow {
        emit(AppResult.Loading(true))
        try {
            val result = block()
            emit(AppResult.Success(result))
        }catch (e: Exception) {
            emit(AppResult.Error(e.message))
        }
        emit(AppResult.Loading(false))
    }.flowOn(dispatchers.io)
}