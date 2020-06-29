package com.wassim.showcase.utils

import com.wassim.showcase.data.remote.ApiService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun okHttpClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    })
    .connectTimeout(5, TimeUnit.SECONDS)
    .addInterceptor(AuthenticationInterceptor())
    .build()

fun apiService(httpClient: OkHttpClient): ApiService = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .client(httpClient)
    .build()
    .create(ApiService::class.java)
