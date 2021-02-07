package com.wrecker.newsapp.di.module

import androidx.recyclerview.widget.RecyclerView
import com.wrecker.newsapp.ui.adapter.NewsAdapter
import com.wrecker.newsapp.ui.factory.BaseFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * to provide the NewsAdapter reference
 */
@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Provides
    @Singleton
    fun provideNewsAdapter() = NewsAdapter()

}