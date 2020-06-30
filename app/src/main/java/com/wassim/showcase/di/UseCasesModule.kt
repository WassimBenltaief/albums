package com.wassim.showcase.di

import com.wassim.showcase.features.favorite.usecase.GetAllFavoriteAlbumsUseCase
import com.wassim.showcase.features.favorite.usecase.GetAllFavoriteAlbumsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun providesGetAllFavoriteAlbumsUseCase(
        implementation: GetAllFavoriteAlbumsUseCaseImpl
    ) : GetAllFavoriteAlbumsUseCase
}