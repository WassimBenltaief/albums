package com.wassim.showcase.di

import android.content.Context
import coil.ImageLoader
import com.wassim.showcase.data.local.AlbumsDatabase
import com.wassim.showcase.utils.apiService
import com.wassim.showcase.utils.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttp() = okHttpClient()

    @Provides
    @Singleton
    fun provideApiService(
        client: OkHttpClient
    ) = apiService(client)

    @Provides
    @Singleton
    fun provideAlbumDao(
        @ApplicationContext context: Context
    ) = AlbumsDatabase
        .getInstance(context)
        .albumsDao()

    @Provides
    @Singleton
    fun provideTagsDao(
        @ApplicationContext context: Context
    ) = AlbumsDatabase
        .getInstance(context)
        .tagsDao()

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context
    ) = ImageLoader.Builder(context)
        .availableMemoryPercentage(0.25)
        .crossfade(true)
        .build()
}