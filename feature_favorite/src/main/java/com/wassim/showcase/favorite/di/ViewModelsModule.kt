package com.wassim.showcase.favorite.di

import androidx.lifecycle.ViewModel
import com.wassim.showcase.core.di.ViewModelKey
import com.wassim.showcase.favorite.usecase.GetAllFavoriteAlbumsUseCase
import com.wassim.showcase.favorite.view.FavoriteAlbumsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
object ViewModelsModule {

    @Provides
    @IntoMap
    @ViewModelKey(FavoriteAlbumsViewModel::class)
    internal fun albumListViewModel(
        getAllFavoriteAlbums: GetAllFavoriteAlbumsUseCase
    ): ViewModel = FavoriteAlbumsViewModel(getAllFavoriteAlbums)
}
