package com.wrecker.newsapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wrecker.newsapp.db.entity.Article


data class NewsResponse(
    var id: Int,
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)