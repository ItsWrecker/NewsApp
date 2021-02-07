package com.wrecker.newsapp.di.module


import com.wrecker.newsapp.db.source.cache.Cache
import com.wrecker.newsapp.db.source.cache.ExpCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    /**
     * To provide cache references to entire application
     */
//    @Provides
//    @Singleton
//    fun provideExpCache()= ExpCache()

}