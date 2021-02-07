package com.wrecker.newsapp.ui.main


import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.wrecker.newsapp.db.entity.Article
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
    private val _eventMain = MutableStateFlow<MainStateEvent>(MainStateEvent.None)
    val eventMain: StateFlow<MainStateEvent> = _eventMain

    var pageNumber = 1


    init {
        viewModelScope.launch {
            _eventMain.collect {
                when(it){
                    MainStateEvent.GetArticle -> {
                        setStateEvent(it,pageNumber)
                    }
                    MainStateEvent.None -> {
                        setStateEvent(it,0)
                        _eventMain.value = MainStateEvent.None
                    }
                    MainStateEvent.Error -> {
                        _eventMain.value = MainStateEvent.Error
                    }
                }
            }
        }
    }

    suspend fun setStateEvent(mainStateEvent: MainStateEvent, page: Int) = viewModelScope.launch {
        _event.value = Event.Loading
        pageNumber = page

            when(mainStateEvent){
                MainStateEvent.GetArticle ->{
                    repositories.getArticle(page).onEach {
                        _event.value = it
                    }.launchIn(viewModelScope)
                }
                MainStateEvent.None -> {
                    _event.value = Event.Loading
                }
                MainStateEvent.Error -> {
                    _eventMain.value = MainStateEvent.Error
                }
            }
        }
    fun showProgressBar(progressBar: ProgressBar, visibility: Boolean) {
        if (visibility) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }
}


