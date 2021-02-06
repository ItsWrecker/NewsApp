package com.wrecker.newsapp.ui.main


import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    var pageNumber = 1


    init {
//        viewModelScope.launch {
//            setStateEvent(MainStateEvent.None, 0)
//        }
    }

    suspend fun setStateEvent(mainStateEvent: MainStateEvent, page: Int) = viewModelScope.launch {
            _event.value = Event.Loading
            when(mainStateEvent){
                MainStateEvent.GetArticle ->{
                    repositories.getArticle(page).onEach {
                        _event.value = it
                    }.launchIn(viewModelScope)
                }
                MainStateEvent.None -> {
                    _event.value = Event.Loading
                }
            }
        }
    fun showProgressBar(progressBar: ProgressBar, visibility: Boolean) {
        if (visibility) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }
}


