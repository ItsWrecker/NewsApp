package com.wrecker.newsapp.db.source.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.Source
import com.wrecker.newsapp.db.source.local.database.NewsDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ArticleDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @Inject
    @Named("test_db")
    lateinit var newsDatabase: NewsDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    fun setup() {
        hiltRule.inject()
        articleDao = newsDatabase.getArticleDao()
    }

    @After
    fun tearDown() {
        newsDatabase.close()
    }


    @Test
    fun insertArticle() = runBlockingTest {
        val article = Article(
            id = 1,
            source = Source("Random","Random"),
            publishedAt = "Date",
            description = "Description",
            content = "Content",
            url = "url",
            urlToImage ="urlToImage",
            title = "title",
            author = "author"
        )
        articleDao.insertArticle(article)

        val allArticles = articleDao.getArticle().take(1).toList().first()
        print(allArticles)
        /**
         * after insertion, it must contain inside the database
         */
        assertThat(allArticles).contains(article)
    }

    @Test
    fun deleteArticle() = runBlockingTest {
        val article = Article(
            id = 1,
            source = Source("Random","Random"),
            publishedAt = "Date",
            description = "Description",
            content = "Content",
            url = "url",
            urlToImage ="urlToImage",
            title = "title",
            author = "author"
        )
        articleDao.insertArticle(article)
        articleDao.deleteArticle(article)

        val allArticles = articleDao.getArticle().take(1).toList().first()
        assertThat(allArticles).doesNotContain(article)

    }


}