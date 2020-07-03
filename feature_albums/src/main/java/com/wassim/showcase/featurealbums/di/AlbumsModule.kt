package com.wassim.showcase.featurealbums.di

import androidx.lifecycle.ViewModel
import com.wassim.showcase.core.di.ViewModelKey
import com.wassim.showcase.featurealbums.view.item.usecase.GetAlbumInfoUseCase
import com.wassim.showcase.featurealbums.view.item.usecase.GetAlbumInfoUseCaseImpl
import com.wassim.showcase.featurealbums.view.item.usecase.SaveAlbumUseCase
import com.wassim.showcase.featurealbums.view.item.usecase.SaveAlbumUseCaseImpl
import com.wassim.showcase.featurealbums.view.item.view.AlbumViewModel
import com.wassim.showcase.featurealbums.view.list.usecase.GetAlbumsUseCase
import com.wassim.showcase.featurealbums.view.list.usecase.GetAlbumsUseCaseImpl
import com.wassim.showcase.featurealbums.view.list.view.AlbumListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@DisableInstallInCheck
@Module
abstract class AlbumsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumListViewModel::class)
    abstract fun albumListViewModel(
        viewModel: AlbumListViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumViewModel::class)
    abstract fun albumViewModel(
        viewModel: AlbumViewModel
    ): ViewModel

    @Binds
    abstract fun providesGetAlbumsUseCase(
        implementation: GetAlbumsUseCaseImpl
    ): GetAlbumsUseCase

    @Binds
    abstract fun providesGetAlbumInfoUseCase(
        implementation: GetAlbumInfoUseCaseImpl
    ): GetAlbumInfoUseCase

    @Binds
    abstract fun providesSaveAlbumUseCase(
        implementation: SaveAlbumUseCaseImpl
    ): SaveAlbumUseCase
}
