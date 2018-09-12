package com.alexeyreznik.movies.data.api

import android.content.Context
import com.alexeyreznik.movies.R

interface ApiKeyProvider {
    fun provideApiKey(): String
}

class ApiKeyProviderImpl(private val context: Context) : ApiKeyProvider {

    override fun provideApiKey(): String {
        return context.getString(R.string.tmdb_api_key)
    }

}