package com.wrecker.newsapp.ui.main

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.spy

@HiltAndroidTest
class MainActivityTest {
    private lateinit var mainActivitySpy: MainActivity

    @get:Rule
    var hiltTestRule = HiltAndroidRule(this)


    @Before
    fun setup() {
        hiltTestRule.inject()
        mainActivitySpy = spy(MainActivity())
    }
    @After
    fun tearDown() {
    }

}