package com.wassim.showcase.utils

import com.wassim.showcase.utils.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        val url = it.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("format", "json")
            .build()

        val newRequest = it.newBuilder()
            .url(url)
            .build()

        chain.proceed(newRequest)
    }
}
