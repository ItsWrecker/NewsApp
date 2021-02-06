package com.wrecker.newsapp.ui.fragments


import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wrecker.newsapp.R
import com.wrecker.newsapp.ui.adapter.NewsAdapter
import com.wrecker.newsapp.ui.main.MainViewModel
import com.wrecker.newsapp.ut.event.Event
import com.wrecker.newsapp.ut.event.MainStateEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class NewsHeadlineFragment : Fragment(R.layout.fragment_news_headlines) {
    private val _viewModel: MainViewModel by viewModels()

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsHeadlineRecyclerView: RecyclerView
    private lateinit var refreshRecyclerView: SwipeRefreshLayout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.setStateEvent(MainStateEvent.GetArticle)
        newsHeadlineRecyclerView = view.findViewById(R.id.newsHeadlineRecyclerView)
        refreshRecyclerView = view.findViewById(R.id.refreshRecyclerView)
        setupRecyclerView()
        refreshView()
        lifecycleScope.launchWhenStarted {
            _viewModel.event.collect { event->
                when(event){
                    is Event.Success -> {
                        newsAdapter.differ.submitList(event.value)
                        refreshRecyclerView.isRefreshing = false

                    }
                    is Event.Error -> {

                    }
                    Event.Loading -> {

                    }
                }
            }
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                /**
                 * Reset the resources:
                 */

                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        newsHeadlineRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun refreshView() {
        refreshRecyclerView.setOnRefreshListener {
            refreshRecyclerView.isRefreshing = true
            _viewModel.setStateEvent(MainStateEvent.GetArticle)
            refreshRecyclerView.isRefreshing = false

        }
    }

}