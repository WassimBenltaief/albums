package com.wassim.showcase.core.di

import coil.ImageLoader
import com.wassim.showcase.core.data.local.AlbumsDao
import com.wassim.showcase.core.data.local.TagsDao
import com.wassim.showcase.core.data.remote.ApiService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CoreComponent {
    fun okHttp(): OkHttpClient
    fun apiService(): ApiService
    fun albumsDao(): AlbumsDao
    fun tagsDao(): TagsDao
    fun imageLoader(): ImageLoader
}
