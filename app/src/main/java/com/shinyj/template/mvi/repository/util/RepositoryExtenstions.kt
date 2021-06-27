package com.shinyj.template.mvi.repository

import com.shinyj.kkp.books.cache.util.CacheResult
import com.shinyj.template.mvi.cache.util.CacheConstants.CACHE_TIMEOUT
import com.shinyj.template.mvi.cache.util.CacheConstants.ERROR_CACHE_TIMEOUT
import com.shinyj.template.mvi.cache.util.CacheConstants.ERROR_CACHE_UNKNOWN
import com.shinyj.template.mvi.network.util.NetworkConstants.ERROR_NETWORK_TIMEOUT
import com.shinyj.template.mvi.network.util.NetworkConstants.ERROR_NETWORK_UNKNOWN
import com.shinyj.template.mvi.network.util.NetworkConstants.NETWORK_TIMEOUT
import com.shinyj.template.mvi.network.util.NetworkConstants.NETWORK_TIMEOUT_ERROR_CODE
import com.shinyj.template.mvi.network.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

suspend fun <T> safeCacheCall(
    dispatcher: CoroutineDispatcher,
    cacheCall: suspend () -> T?
): CacheResult<T?> {
    return withContext(dispatcher){
        try {
            withTimeout(CACHE_TIMEOUT){
                CacheResult.Success(cacheCall.invoke())
            }
        } catch (e : Throwable){
            e.printStackTrace()
            when(e){
                is TimeoutCancellationException -> {
                    CacheResult.GenericError(ERROR_CACHE_TIMEOUT)
                }
                else -> {
                    CacheResult.GenericError(ERROR_CACHE_UNKNOWN)
                }
            }
        }
    }
}

suspend fun <T> safeNetworkCall(
    dispatcher: CoroutineDispatcher,
    networkCall: suspend () -> T?
): NetworkResult<T?> {
    return withContext(dispatcher){
        try {
            withTimeout(NETWORK_TIMEOUT){
                NetworkResult.Success(networkCall.invoke())
            }
        } catch (e : Throwable){
            e.printStackTrace()
            when(e){
                is TimeoutCancellationException -> {
                    NetworkResult.GenericError(NETWORK_TIMEOUT_ERROR_CODE, ERROR_NETWORK_TIMEOUT)
                }
                is IOException -> {
                    NetworkResult.NetworkError
                }

                //TODO: Add HttpException here

                else -> {
                    NetworkResult.GenericError(null, ERROR_NETWORK_UNKNOWN)
                }
            }
        }
    }
}