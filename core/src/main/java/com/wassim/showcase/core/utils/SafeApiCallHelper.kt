package com.wassim.showcase.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Wrap a suspending API [call] in a try/catch.
 * returns either a [Result.Success] or [Result.Error].
 */
suspend fun <T : Any> safeApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> T
): Result<T> {
    return try {
        val result = withContext(dispatcher) { call() }
        Result.Success(result)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
