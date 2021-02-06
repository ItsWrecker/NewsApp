package com.wrecker.newsapp.di.module

import androidx.fragment.app.FragmentFactory
import com.wrecker.newsapp.ui.adapter.NewsAdapter
import com.wrecker.newsapp.ui.factory.BaseFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FactoryModule {

    @Provides
    @Singleton
    fun provideBaseFragmentFactory(
        newsAdapter: NewsAdapter
    ) : FragmentFactory {
        return BaseFragmentFactory(newsAdapter)
    }
}