package com.wrecker.newsapp.di

import android.content.Context
import androidx.room.Room
import com.wrecker.newsapp.db.source.local.dao.ArticleDao
import com.wrecker.newsapp.db.source.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Named("test_db")
    @Provides
    fun provideInMemoryDB(
        @ApplicationContext context: Context
    ) : NewsDatabase = Room.inMemoryDatabaseBuilder(
        context,
        NewsDatabase::class.java
    ).allowMainThreadQueries().build()


    @Named("test_article_dao")
    @Provides
    fun provideArticleDao(
        newsDatabase: NewsDatabase
    ): ArticleDao = newsDatabase.getArticleDao()
}

