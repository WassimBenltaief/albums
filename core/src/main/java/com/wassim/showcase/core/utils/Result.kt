package com.wassim.showcase.utils

/**
 * Wrapper for encapsulating the api call results.
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
