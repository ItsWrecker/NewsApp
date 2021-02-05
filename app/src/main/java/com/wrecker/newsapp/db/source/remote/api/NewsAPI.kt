package com.wrecker.newsapp.db.source.remote.api

import com.wrecker.newsapp.BuildConfig
import com.wrecker.newsapp.db.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getNewsResponse(
        @Query("country")
        country: String = "in",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.NEWS_API_KEY
    ): Response<NewsResponse>
}