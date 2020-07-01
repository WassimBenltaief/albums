package com.wassim.showcase.favorite.di

import com.wassim.showcase.favorite.usecase.GetAllFavoriteAlbumsUseCase
import com.wassim.showcase.favorite.usecase.GetAllFavoriteAlbumsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class FavoriteModule {

    @Binds
    abstract fun providesGetAllFavoriteAlbumsUseCase(
        implementation: GetAllFavoriteAlbumsUseCaseImpl
    ): GetAllFavoriteAlbumsUseCase
}
