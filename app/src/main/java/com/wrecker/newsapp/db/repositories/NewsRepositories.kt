package com.wrecker.newsapp.db.repositories

import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.mapper.NetWorkMapper
import com.wrecker.newsapp.db.source.local.dao.ArticleDao
import com.wrecker.newsapp.db.source.remote.api.NewsAPI
import com.wrecker.newsapp.ut.event.Event
import com.wrecker.newsapp.ut.event.MainStateEvent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    override suspend fun getArticle(pageNumber: Int): Flow<Event<List<Article>>> {
        return flow {
            emit(Event.Loading)
            try {
                val localResponse = articleDao.getArticle()
                val remoteResponse = newsAPI.getNewsResponse(page = pageNumber)
                val mappedRemoteResponse = netWorkMapper.fromAPI(remoteResponse.body()!!)

                /**
                 * If local response already contains the remote response
                 */
                if (localResponse.isNotEmpty() && localResponse.contains(mappedRemoteResponse)){
                    /**
                     * Here simply emits  the localResponse
                     */
                    emit(Event.Success(localResponse))

                }
                /**
                 * If local response doesn't contains the recent response from API.
                 */
                else if (localResponse.isNotEmpty() && !localResponse.contains(mappedRemoteResponse)){
                    /**
                     * Inserting the unique response only, to the local cache
                     */
                    mappedRemoteResponse.forEach {
                        articleDao.insertArticle(it)
                    }
                    emit(Event.Success(articleDao.getArticle()))
                } else {
                    /**
                     * Inserting the data into local cache and emits to stream
                     */
                    mappedRemoteResponse.forEach {
                        articleDao.insertArticle(it)
                    }
                    emit(Event.Success(articleDao.getArticle()))
                }
            }catch (exception: Exception){
                emit(Event.Error("Not good",exception))
            }
        }
    }


}


