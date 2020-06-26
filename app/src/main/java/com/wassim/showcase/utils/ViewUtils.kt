package com.wassim.showcase.utils

import android.content.Context
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Wraps the inflation logic
 */
fun Context.inflate(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
): View = LayoutInflater.from(this).inflate(layoutId, parent, false)

/**
 * A delegate for observing changes on lists and applying lambda expression
 */
inline fun <T> observer(
    initialValue: T,
    crossinline onChange: (newValue: T) -> Unit
): ReadWriteProperty<Any?, T> =

    object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) =
            onChange(newValue)
    }

/**
 * Utility click listener that accept only one click during [throttleTime]
 */
fun View.onClickWithThrottle(throttleTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < throttleTime) return
            else action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

private fun <E> List<E>.second() = this[1]