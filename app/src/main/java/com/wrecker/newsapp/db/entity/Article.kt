package com.wrecker.newsapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Article entity is the key entity for performing the
 * operation over local and remote data
 */
@Entity
data class Article(
    val author: String? =null,
    val content: String? = null,
    val description: String ? =null,
    /**
     * Choosing this as primary key, we don't
     * have any other unique attribute from api response.
     */
    @PrimaryKey
    val publishedAt: String,
    val source: Source ? =null,
    val title: String ? =null,
    val url: String ? =null,
    val urlToImage: String ? =null
): Serializable
/**
 * Serializable for parsing through navigation.
 */
