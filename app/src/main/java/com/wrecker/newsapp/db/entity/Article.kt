package com.wrecker.newsapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Article(
    val author: String? =null,
    val content: String? = null,
    val description: String ? =null,
    @PrimaryKey
    val publishedAt: String,
    val source: Source ? =null,
    val title: String ? =null,
    val url: String ? =null,
    val urlToImage: String ? =null
): Serializable