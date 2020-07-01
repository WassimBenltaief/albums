package com.wassim.showcase.favorite.di

import com.wassim.showcase.core.di.DynamicFeaturesDependencies
import com.wassim.showcase.favorite.view.FavoriteAlbumsFragment
import dagger.Component

@Component(
    modules = [FavoriteModule::class, ViewModelsModule::class],
    dependencies = [DynamicFeaturesDependencies::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteAlbumsFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DynamicFeaturesDependencies): FavoriteComponent
    }
}
