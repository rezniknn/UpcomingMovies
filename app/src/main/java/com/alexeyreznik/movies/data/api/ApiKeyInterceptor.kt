package com.alexeyreznik.movies.data.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKeyProvider: ApiKeyProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var newUrl = request.url().toString()
        if (request.url().uri().query != null) newUrl = newUrl.plus("&")
        else newUrl = newUrl.plus("?")

        newUrl = newUrl.plus("$KEY_API_KEY=${apiKeyProvider.provideApiKey()}")

        val newRequest = request.newBuilder()
                .url(newUrl)
                .build()

        return chain.proceed(newRequest)
    }

    companion object {
        const val KEY_API_KEY = "api_key"
    }
}