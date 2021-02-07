package com.wrecker.newsapp.di.module

import com.wrecker.newsapp.BuildConfig
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.NewsResponse
import com.wrecker.newsapp.db.mapper.EntityMapper
import com.wrecker.newsapp.db.mapper.NetWorkMapper
import com.wrecker.newsapp.db.source.remote.api.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Providing the references to all network request related operation
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * reference to API call
     */
    @Provides
    @Singleton
    fun provideNewsAPI(): NewsAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.NEWS_API_BASE_URL).build().create(NewsAPI::class.java)
    }

    /**
     * reference to entity mapper
     */
    @Provides
    @Singleton
    fun provideNetworkMapper(): EntityMapper<NewsResponse, Article> =  NetWorkMapper()

}