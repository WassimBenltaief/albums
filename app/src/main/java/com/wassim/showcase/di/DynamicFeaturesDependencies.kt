package com.wassim.showcase.di

import coil.ImageLoader
import com.wassim.showcase.data.local.AlbumsDao
import com.wassim.showcase.data.local.TagsDao
import com.wassim.showcase.data.remote.ApiService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface DynamicFeaturesDependencies {

    fun okHttp() : OkHttpClient
    fun apiService(): ApiService
    fun albumsDao(): AlbumsDao
    fun tagsDao(): TagsDao
    fun imageLoader(): ImageLoader
}