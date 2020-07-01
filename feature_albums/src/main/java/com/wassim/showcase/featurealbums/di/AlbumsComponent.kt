package com.wassim.showcase.featurealbums.di

import com.wassim.showcase.featurealbums.view.item.view.AlbumFragment
import com.wassim.showcase.featurealbums.view.list.view.AlbumListFragment
import com.wassim.showcase.di.DynamicFeaturesDependencies
import dagger.Component
import dagger.hilt.android.scopes.ActivityScoped

@Component(
    modules = [UseCasesModule::class, ViewModelsModule::class],
    dependencies = [DynamicFeaturesDependencies::class]
)
interface AlbumsComponent {

    fun inject(fragment: AlbumListFragment)
    fun inject(fragment: AlbumFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DynamicFeaturesDependencies): AlbumsComponent
    }
}