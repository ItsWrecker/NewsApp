package com.wrecker.newsapp.db.source.cache
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.ut.event.Event
import dagger.Module
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * For performing the caching logic : expiry time => 2 hours
 */
interface  Cache {
    val size: Int

    operator fun set(key: Any, value: Any)

    operator fun get(key: Any): List<Article>

    fun remove(key: Any): Any?

    fun clear()
}