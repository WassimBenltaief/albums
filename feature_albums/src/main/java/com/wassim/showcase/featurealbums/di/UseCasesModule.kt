package com.wassim.showcase.featurealbums.di

import androidx.lifecycle.ViewModelProvider
import com.wassim.showcase.featurealbums.view.item.usecase.GetAlbumInfoUseCase
import com.wassim.showcase.featurealbums.view.item.usecase.GetAlbumInfoUseCaseImpl
import com.wassim.showcase.featurealbums.view.item.usecase.SaveAlbumUseCase
import com.wassim.showcase.featurealbums.view.item.usecase.SaveAlbumUseCaseImpl
import com.wassim.showcase.featurealbums.view.list.usecase.GetAlbumsUseCase
import com.wassim.showcase.featurealbums.view.list.usecase.GetAlbumsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class UseCasesModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

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
