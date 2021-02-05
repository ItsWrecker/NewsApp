package com.wrecker.newsapp.di.module

import android.content.Context
import androidx.room.Room
import com.wrecker.newsapp.BuildConfig
import com.wrecker.newsapp.db.source.local.dao.ArticleDao
import com.wrecker.newsapp.db.source.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsDatabaseModule {


    @Singleton
    @Provides
    fun provideNewsDatabase(
     @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        BuildConfig.NEWS_DB_NAME
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


    @Singleton
    @Provides
    fun provideArticleDao(
        newsDatabase: NewsDatabase
    ): ArticleDao = newsDatabase.getArticleDao()
}