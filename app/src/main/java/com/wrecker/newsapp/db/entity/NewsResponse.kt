package com.wrecker.newsapp.db.entity


data class NewsResponse(
    var id: Int,
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)