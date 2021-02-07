package com.wrecker.newsapp.db.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.source.local.dao.ArticleDao

/**
 * Reference of local database
 */
@Database(
    entities = [Article::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao
}