package com.wrecker.newsapp.db.source.local.dao

import androidx.room.*
import com.wrecker.newsapp.db.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article): Long

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM article ORDER BY publishedAt DESC")
    fun getArticle(): List<Article>
}