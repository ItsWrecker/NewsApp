package com.wrecker.newsapp.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.NewsResponse
import com.wrecker.newsapp.db.repositories.Repository
import com.wrecker.newsapp.ut.event.Event
import com.wrecker.newsapp.ut.event.MainStateEvent

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repositories: Repository
): ViewModel() {

    private val _event = MutableStateFlow<Event<List<Article>>>(Event.Loading)
    val event: StateFlow<Event<List<Article>>> = _event

    private val _articles = MutableStateFlow<Event<NewsResponse>>(Event.Loading)
    val articles: StateFlow<Event<NewsResponse>> = _articles


     fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                MainStateEvent.GetArticle ->{
                    repositories.getArticle().onEach {
                        _event.value = it
                    }.launchIn(viewModelScope)
                }
                MainStateEvent.None -> {
                    _event.value = Event.Loading
                }
            }
        }
    }



}