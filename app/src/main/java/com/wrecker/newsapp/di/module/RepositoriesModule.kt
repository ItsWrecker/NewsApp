package com.wrecker.newsapp.di.module

import com.wrecker.newsapp.db.repositories.NewsRepositories
import com.wrecker.newsapp.db.repositories.Repository
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

    @Provides
    @ViewModelScoped
    fun provideNewsRepository(
        articleDao: ArticleDao,
        newsAPI: NewsAPI
    ) : Repository = NewsRepositories(articleDao = articleDao, newsAPI = newsAPI)
}