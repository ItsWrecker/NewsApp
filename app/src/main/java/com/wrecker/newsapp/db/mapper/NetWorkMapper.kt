package com.wrecker.newsapp.db.mapper

import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.NewsResponse
import javax.inject.Inject


class NetWorkMapper
@Inject constructor() : EntityMapper<NewsResponse, Article> {
    override fun fromAPI(newsResponse: NewsResponse): List<Article> {
        return newsResponse.articles
    }
}
