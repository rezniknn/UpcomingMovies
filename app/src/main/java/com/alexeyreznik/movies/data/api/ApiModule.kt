package com.alexeyreznik.movies.data.api

import android.content.Context
import com.alexeyreznik.movies.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiModule {

    fun build(applicationContext: Context) = module {

        single<ApiKeyProvider> { ApiKeyProviderImpl(applicationContext) }

        single<OkHttpClient> {
            OkHttpClient.Builder()
                    .addInterceptor(ApiKeyInterceptor(get()))
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()
        }

        single<MoviesApi> {
            val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(applicationContext.getString(R.string.tmdb_base_url))
                    .client(get())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()

            retrofit.create(MoviesApi::class.java)
        }
    }
}