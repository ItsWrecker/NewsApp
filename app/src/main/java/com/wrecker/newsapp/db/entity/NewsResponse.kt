package com.wrecker.newsapp.db.entity


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)