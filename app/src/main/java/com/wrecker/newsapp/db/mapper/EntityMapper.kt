package com.wrecker.newsapp.db.mapper

import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.NewsResponse

interface EntityMapper<NewsResponse, Article> {
    fun fromAPI(newsResponse: NewsResponse) : List<Article>

}