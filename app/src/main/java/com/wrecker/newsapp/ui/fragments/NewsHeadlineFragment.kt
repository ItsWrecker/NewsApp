package com.wrecker.newsapp.ui.fragments


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Displaying the top news headline: (Just basic operation) will extend to this part
 */
@AndroidEntryPoint
class NewsHeadlineFragment : Fragment(R.layout.fragment_news_headlines) {
    private val _viewModel: MainViewModel by viewModels()

    /**
     * Getting the reference for adapter
     */
    @Inject
    lateinit var newsAdapter: NewsAdapter
    private lateinit var newsHeadlineRecyclerView: RecyclerView
    private lateinit var refreshRecyclerView: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private var pageNumber: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsHeadlineRecyclerView = view.findViewById(R.id.newsHeadlineRecyclerView)
        refreshRecyclerView = view.findViewById(R.id.refreshRecyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        (activity as AppCompatActivity).supportActionBar?.show()
        setupRecyclerView()
        handleNavigation()

        /**
         * Launching the coroutine for collect the flow stream data to perform UI rendering accordingly
         */
        lifecycleScope.launchWhenStarted {
            /**
             * Refreshing the view every time anything gets change.
             */
            refreshView()
            /**
             * sending the event to view model, requesting for data to load
             */
            _viewModel.setStateEvent(MainStateEvent.GetArticle, pageNumber)
            /**
             * collecting the data emmited by domain layer
             */
            _viewModel.event.collect { event->
                when(event){
                    /**
                     * for every success full emmition it will update the adapter
                     */
                    is Event.Success -> {
                        refreshRecyclerView.isRefreshing = true
                        newsAdapter.differ.submitList(event.value)
                        refreshRecyclerView.isRefreshing = false
                    }
                    /**
                     * on error received fom domain layer, navigation to the error page
                     */
                    is Event.Error -> {
                        _viewModel.showProgressBar(progressBar, false)
                        findNavController().navigate(R.id.action_newsDetailsFragment_to_errorFragment)
                    }
                    /**
                     * displaying the progress bar till data is being fetched from domain layer
                     */
                    Event.Loading -> {
                          progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
        /**
         * callback for performing the onBackPress event
         */
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                /**
                 * Reset the resources: will update after implementing the UI Cache.
                 */

                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    /**
     * Setting up recyclerview
     */
     private fun  setupRecyclerView() {

        newsHeadlineRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    /**
     * refreshing the recyclerview when either new data comes or user want to pull screen
     */
    private suspend fun refreshView() = GlobalScope.launch{
        refreshRecyclerView.isRefreshing = true
        pageNumber += 1
        refreshRecyclerView.setOnRefreshListener {
            GlobalScope.launch {
                _viewModel.setStateEvent(MainStateEvent.GetArticle, pageNumber)
            }.start()

            refreshRecyclerView.isRefreshing = false
        }
    }

    /**
     * handling the navigation with [nav args]
     */
    private fun handleNavigation() {
        newsAdapter.setOnClickListener {
            val bundle: Bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_newsHeadlineFragment_to_newsDetailsFragment,bundle)
        }
    }

}