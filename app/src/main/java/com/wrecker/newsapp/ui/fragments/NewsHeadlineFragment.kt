package com.wrecker.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wrecker.newsapp.R
import com.wrecker.newsapp.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsHeadlineFragment : Fragment(R.layout.fragment_news_headlines) {
    private val _viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
}