package com.alexeyreznik.movies.data.api

import com.squareup.moshi.Json

class ApiResponse<out T>(@Json(name = "results") val results: T?,
                         @Json(name = "status_code") val statusCode: Int?,
                         @Json(name = "status_message") val statusMessage: String?)