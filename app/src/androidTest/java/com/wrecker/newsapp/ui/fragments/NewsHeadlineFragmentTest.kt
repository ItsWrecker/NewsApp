package com.wrecker.newsapp.ui.fragments

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.wrecker.newsapp.R
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.db.entity.Source
import com.wrecker.newsapp.launchFragmentInHiltContainer
import com.wrecker.newsapp.ui.adapter.NewsAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import javax.inject.Inject
import javax.inject.Named
import com.google.common.truth.Truth.assertThat
import com.wrecker.newsapp.db.source.local.dao.ArticleDao

@MediumTest
@HiltAndroidTest
class NewsHeadlineFragmentTest {

    @get:Rule
    var hiltTestRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltTestRule.inject()
    }

    @Test
    fun onclickHeadline_navigateToDetails() {
        val navController = mock(NavController::class.java)
        val article = Article(
            source = Source("Random","Random"),
            publishedAt = "Date",
            description = "Description",
            content = "Content",
            url = "url",
            urlToImage ="urlToImage",
            title = "title",
            author = "author"
        )
        val bundle = Bundle().apply {
            putSerializable("article", article)
        }
        launchFragmentInHiltContainer<NewsHeadlineFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        /**
         *
         */
        onView(withId(R.id.newPreviewImage)).perform(click())
        verify(navController).navigate(
            R.id.action_newsHeadlineFragment_to_newsDetailsFragment, bundle
        )


    }

    @Test
    fun onViewCreated() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.wrecker.newsapp", appContext.packageName)
    }

    @Inject
    @Named("test_news_adapter")
    lateinit var adapter: NewsAdapter
    @Test
    fun getNewsAdapter() {
            assertThat(adapter).isNotNull()
    }

    @Inject
    @Named("test_article_dao")
    lateinit var articleDao: ArticleDao
    @Test
    fun refreshView() {
        assertThat(articleDao).isNotNull()
    }

}