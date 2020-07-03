package com.wassim.showcase.core.di

import androidx.fragment.app.Fragment
import dagger.hilt.android.EntryPointAccessors

fun coreInstance(fragment: Fragment): CoreComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext(),
        CoreComponent::class.java
    )
