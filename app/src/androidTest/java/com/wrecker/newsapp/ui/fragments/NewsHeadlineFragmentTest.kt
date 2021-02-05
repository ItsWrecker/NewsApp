package com.wrecker.newsapp.ui.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.wrecker.newsapp.R
import com.wrecker.newsapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


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

        launchFragmentInHiltContainer<NewsHeadlineFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.nav_host_fragment_container)).perform(click())



    }
}