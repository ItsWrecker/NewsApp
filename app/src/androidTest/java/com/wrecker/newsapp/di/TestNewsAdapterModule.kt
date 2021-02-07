package com.wrecker.newsapp.di

import com.wrecker.newsapp.ui.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestNewsAdapterModule{

    @Named("test_news_adapter")
    @Provides
    fun provideNewsAdapter() = NewsAdapter()

}