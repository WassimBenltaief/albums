package com.wassim.showcase.di

import coil.ImageLoader
import com.wassim.showcase.features.albums.item.usecase.GetAlbumInfoUseCase
import com.wassim.showcase.features.albums.item.usecase.GetAlbumInfoUseCaseImpl
import com.wassim.showcase.features.albums.item.view.AlbumViewModel
import com.wassim.showcase.features.albums.list.usecase.GetAlbumsUseCase
import com.wassim.showcase.features.albums.list.usecase.GetAlbumsUseCaseImpl
import com.wassim.showcase.features.albums.list.view.AlbumListViewModel
import com.wassim.showcase.utils.apiService
import com.wassim.showcase.utils.okHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AlbumListViewModel(get())
    }
    viewModel {
        AlbumViewModel(get())
    }
    factory<GetAlbumsUseCase> {
        GetAlbumsUseCaseImpl(get())
    }
    factory<GetAlbumInfoUseCase> {
        GetAlbumInfoUseCaseImpl(get())
    }
}

val networkModule = module {
    single {
        okHttpClient()
    }
    single {
        apiService(get())
    }
}

val imageLoader = module {
    single {
        ImageLoader.Builder(get())
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
    }
}