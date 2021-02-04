package com.wrecker.newsapp.di.module

import com.wrecker.newsapp.BuildConfig
import com.wrecker.newsapp.db.source.remote.api.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNewsAPI(): NewsAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.NEWS_API_BASE_URL).build().create(NewsAPI::class.java)
    }

}