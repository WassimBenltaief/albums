package com.wassim.showcase.favorite.di

import androidx.lifecycle.ViewModel
import com.wassim.showcase.core.di.ViewModelKey
import com.wassim.showcase.favorite.usecase.GetAllFavoriteAlbumsUseCase
import com.wassim.showcase.favorite.usecase.GetAllFavoriteAlbumsUseCaseImpl
import com.wassim.showcase.favorite.view.FavoriteAlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
abstract class FavoriteModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteAlbumsViewModel::class)
    abstract fun favoriteAlbumsViewModel(
        viewModel: FavoriteAlbumsViewModel
    ): ViewModel

    @Binds
    abstract fun providesGetAllFavoriteAlbumsUseCase(
        implementation: GetAllFavoriteAlbumsUseCaseImpl
    ): GetAllFavoriteAlbumsUseCase
}
