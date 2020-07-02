package com.wassim.showcase.featurealbums.di

import com.wassim.showcase.core.di.CoreComponent
import com.wassim.showcase.core.di.FeatureScope
import com.wassim.showcase.core.di.ViewModelFactoryModule
import com.wassim.showcase.featurealbums.view.item.view.AlbumFragment
import com.wassim.showcase.featurealbums.view.list.view.AlbumListFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelFactoryModule::class, AlbumsModule::class]
)
interface AlbumsComponent {

    fun inject(fragment: AlbumListFragment)
    fun inject(fragment: AlbumFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: CoreComponent): AlbumsComponent
    }
}
