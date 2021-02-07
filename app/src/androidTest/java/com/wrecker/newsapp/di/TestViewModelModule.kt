package com.wrecker.newsapp.di

import androidx.lifecycle.ViewModel
import com.wrecker.newsapp.db.repositories.NewsRepositories
import com.wrecker.newsapp.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object TestViewModelModule {

    @Provides
    @Named("test_view_model")
    fun provideMainViewModel(
        repositories: NewsRepositories
    ) = MainViewModel(repositories)
}