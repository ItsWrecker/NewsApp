package com.wrecker.newsapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.wrecker.newsapp.MainCoroutineTestRule
import com.wrecker.newsapp.db.repositories.FakeRepositories
import com.wrecker.newsapp.db.repositories.NewsRepositories
import com.wrecker.newsapp.ut.event.MainStateEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineTestRule = MainCoroutineTestRule()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(FakeRepositories())
    }

    @Test
    fun viewModelShouldNotBeNull() {
        assertThat(viewModel).isNotNull()
    }

    @Test fun getArticle() = runBlockingTest {
        val res = viewModel.setStateEvent(MainStateEvent.GetArticle,1)
        assertThat(res).isNotNull()
    }

//    @Test
//    fun getEvent() {
//    }
//
//    @Test
//    fun getPageNumber() {
//    }
//
//    @Test
//    fun setPageNumber() {
//    }
//
//    @Test
//    fun setStateEvent() {
//    }
//
//    @Test
//    fun showProgressBar() {
//    }
//
//    @Test
//    fun getEventMain() {
//    }

}