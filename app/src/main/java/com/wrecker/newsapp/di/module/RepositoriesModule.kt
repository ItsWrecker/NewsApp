package com.wrecker.newsapp.di.module

import com.wrecker.newsapp.db.mapper.NetWorkMapper
import com.wrecker.newsapp.db.repositories.NewsRepositories
import com.wrecker.newsapp.db.repositories.Repository
import com.wrecker.newsapp.db.source.cache.Cache
import com.wrecker.newsapp.db.source.cache.ExpCache
import com.wrecker.newsapp.db.source.local.dao.ArticleDao
import com.wrecker.newsapp.db.source.remote.api.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoriesModule {
    /**
     * providing the repository to the application level component and below
     */
    @Provides
    @ViewModelScoped
    fun provideNewsRepository(
        articleDao: ArticleDao,
        newsAPI: NewsAPI,
        netWorkMapper: NetWorkMapper,
    ) : Repository = NewsRepositories(
        articleDao = articleDao,
        newsAPI = newsAPI,
        netWorkMapper= netWorkMapper,

    )
}