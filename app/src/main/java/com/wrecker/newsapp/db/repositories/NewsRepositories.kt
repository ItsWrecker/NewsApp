package com.wrecker.newsapp.db.repositories

import androidx.room.PrimaryKey
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.mapper.NetWorkMapper
import com.wrecker.newsapp.db.source.local.dao.ArticleDao
import com.wrecker.newsapp.db.source.remote.api.NewsAPI
import com.wrecker.newsapp.ut.event.Event
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class NewsRepositories @Inject constructor(
    private val articleDao: ArticleDao,
    private val newsAPI: NewsAPI,
    private val netWorkMapper: NetWorkMapper
): Repository {
    override suspend fun insertArticle(article: Article): Long {
        return articleDao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        return articleDao.deleteArticle(article)
    }

    override suspend fun getArticle(): Flow<Event<List<Article>>> = flow {
        emit(Event.Loading)
        try {
            emit(Event.Success(articleDao.getArticle()))

        }catch (exception: Exception) {
            emit(Event.Error("Something went wrongs", exception))

        }
    }




}


