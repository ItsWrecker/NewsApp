package com.wrecker.newsapp.db.repositories

import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.ut.event.Event
import kotlinx.coroutines.flow.*

class FakeRepositories : Repository{
    private var article = mutableListOf<Article>()

    private var flowableArticle: Flow<List<Article>> = flow<List<Article>>() {
        emit(article)
    }

    private var shouldReturnNetworkError: Boolean = false


    fun shouldReturnNetworkError(it: Boolean) {
        shouldReturnNetworkError = it
    }

    private fun refreshFlow(): Flow<List<Article>> {
        return this.flowableArticle
    }

    override suspend fun insertArticle(article: Article): Long {
        this.article.add(article)
        return 0
    }

    override suspend fun deleteArticle(article: Article) {
        this.article.remove(article)
    }

    override suspend fun getArticle(): Flow<Event<List<Article>>> {
        return flow {
            this.emit(Event.Loading)
            try {
                this.emit(Event.Success(article))
            } catch (exception: Exception){
                this.emit(Event.Error("Something went wrong: Network"))
            }
        }
    }


}