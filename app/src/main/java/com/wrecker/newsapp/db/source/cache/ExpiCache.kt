package com.wrecker.newsapp.db.source.cache



import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Implementation of Cache
 */
import com.wrecker.newsapp.db.entity.Article

class ExpCache (
    private val delegate: Cache,
    private val flushInterval: Long = TimeUnit.HOURS.toMillis(2)
) : Cache {
    private val lastFlushTime = System.nanoTime()
    override val size: Int
        get() = delegate.size

    private fun recycle() {
        val shouldRecycle = System.nanoTime() - lastFlushTime >= TimeUnit.MILLISECONDS.toNanos(flushInterval)
        if (!shouldRecycle) return
        delegate.clear()
    }
    override fun set(key: Any, value: Any) {
        delegate[key] = value
    }

    override fun get(key: Any): List<Article> {
        recycle()
        return delegate[key]
    }


    override fun remove(key: Any): Any?  {
        recycle()
        return delegate.remove(key)
    }

    override fun clear() = delegate.clear()
}