package com.wassim.showcase.featurealbums.di

import androidx.lifecycle.ViewModel
import com.wassim.showcase.featurealbums.view.item.usecase.GetAlbumInfoUseCase
import com.wassim.showcase.featurealbums.view.item.usecase.SaveAlbumUseCase
import com.wassim.showcase.featurealbums.view.item.view.AlbumViewModel
import com.wassim.showcase.featurealbums.view.list.usecase.GetAlbumsUseCase
import com.wassim.showcase.featurealbums.view.list.view.AlbumListViewModel
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
    @ViewModelKey(AlbumListViewModel::class)
    internal fun albumListViewModel(
        getAlbumInfoUseCase: GetAlbumsUseCase
    ): ViewModel = AlbumListViewModel(getAlbumInfoUseCase)

    @Provides
    @IntoMap
    @ViewModelKey(AlbumViewModel::class)
    internal fun albumViewModel(
        setAlbumInfoUseCase: GetAlbumInfoUseCase,
        saveAlbumUseCase: SaveAlbumUseCase
    ): ViewModel = AlbumViewModel(setAlbumInfoUseCase, saveAlbumUseCase)
}