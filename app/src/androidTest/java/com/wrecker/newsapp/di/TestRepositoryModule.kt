package com.wrecker.newsapp.di

import com.wrecker.newsapp.db.mapper.NetWorkMapper
import com.wrecker.newsapp.db.repositories.NewsRepositories
import com.wrecker.newsapp.db.source.local.dao.ArticleDao
import com.wrecker.newsapp.db.source.remote.api.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestRepositoryModule {

    @Provides
    @Named("test_repository")
    fun provideRepository(
        articleDao: ArticleDao,
        newsAPI: NewsAPI,
        netWorkMapper: NetWorkMapper

    ) = NewsRepositories(
        articleDao,
        newsAPI,
        netWorkMapper

    )
}