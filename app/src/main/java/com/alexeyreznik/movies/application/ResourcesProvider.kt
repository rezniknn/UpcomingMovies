package com.alexeyreznik.movies.application

import android.content.Context

interface ResourcesProvider {

    fun getString(resId: Int): String

}

class ResourcesProviderImpl(private val context: Context) : ResourcesProvider {

    override fun getString(resId: Int): String = context.resources.getString(resId)
}