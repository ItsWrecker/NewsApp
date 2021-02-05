package com.wrecker.newsapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.NewsResponse
import com.wrecker.newsapp.db.repositories.Repository
import com.wrecker.newsapp.ut.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositories: Repository
): ViewModel() {
    private val _event = MutableStateFlow<Event<List<Article>>>(Event.Loading)
    val event: StateFlow<Event<List<Article>>> = _event

    private val _articles = MutableStateFlow<Event<NewsResponse>>(Event.Loading)
    val articles: StateFlow<Event<NewsResponse>> = _articles


    /**
     *
     */
    init {

    }

    fun getArticle() = viewModelScope.launch {
        val response = repositories.getArticle().collect {
            when(it){
                is Event.Success -> {
                    _event.value = Event.Success(it.value)
                }
                is Event.Error -> {
                    _event.value = Event.Error(it.error, it.cause)
                }
                Event.Loading -> {
                    _event.value = Event.Loading
                }
            }
        }

    }
}