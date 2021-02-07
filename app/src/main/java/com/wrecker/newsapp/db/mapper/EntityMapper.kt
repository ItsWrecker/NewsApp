package com.wrecker.newsapp.db.mapper

import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.NewsResponse

/**
 * For mapping the remote response into local response
 */
interface EntityMapper<NewsResponse, Article> {
    fun fromAPI(newsResponse: NewsResponse) : List<Article>

}