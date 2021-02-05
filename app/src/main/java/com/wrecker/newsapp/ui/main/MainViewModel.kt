package com.wrecker.newsapp.ui.main

import androidx.lifecycle.ViewModel
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.NewsResponse
import com.wrecker.newsapp.db.repositories.Repository
import com.wrecker.newsapp.ut.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositories: Repository
): ViewModel() {
    private val _event = MutableStateFlow<Event<Article>>(Event.Loading)
    val event: StateFlow<Event<Article>> = _event

    private val _articles = MutableStateFlow<Event<NewsResponse>>(Event.Loading)
    val articles: StateFlow<Event<NewsResponse>> = _articles



}