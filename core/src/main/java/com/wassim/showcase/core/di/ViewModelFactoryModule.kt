package com.wassim.showcase.core.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface ViewModelFactoryModule {

    @get:[FeatureScope Binds]
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory
}
