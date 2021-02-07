package com.wrecker.newsapp.ut.event

sealed class MainStateEvent {

    object GetArticle: MainStateEvent()
    object None: MainStateEvent()
    object Error: MainStateEvent()
}