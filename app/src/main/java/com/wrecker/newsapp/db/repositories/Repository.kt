package com.wrecker.newsapp.db.repositories


import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.ut.event.Event
import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun insertArticle(article: Article): Long

    suspend fun deleteArticle(article: Article)

    /**
     * Here passing the result of fun into flow: Unidirectional
     */
    suspend fun getArticle(pageNumber: Int): Flow<Event<List<Article>>>
}