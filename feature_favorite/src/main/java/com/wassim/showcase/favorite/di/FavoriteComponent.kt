package com.wassim.showcase.favorite.di

import com.wassim.showcase.core.di.CoreComponent
import com.wassim.showcase.core.di.FeatureScope
import com.wassim.showcase.core.di.ViewModelFactoryModule
import com.wassim.showcase.favorite.view.FavoriteAlbumsFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelFactoryModule::class, FavoriteModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteAlbumsFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: CoreComponent): FavoriteComponent
    }
}
