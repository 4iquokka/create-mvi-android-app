package com.shinyj.kkp.books.cache.util

sealed class CacheResult<out T> {

    data class Success<out T>(val value : T) : CacheResult<T>()

    data class GenericError(
        val errorMessage: String? = null
    ) : CacheResult<Nothing>()

}