package com.wassim.showcase.testshared

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Observing live data during tests and removing observer at the end.
 */
fun <T> LiveData<T>.observeForTesting(observer: Observer<T>, call: (Observer<T>) -> Unit) {
    this.observeForever(observer)
    call(observer)
    this.removeObserver(observer)
}
