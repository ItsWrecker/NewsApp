package com.wrecker.newsapp.ui.fragments
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wrecker.newsapp.R
import com.wrecker.newsapp.databinding.FragmentNewsDetailsBinding
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.ui.main.MainViewModel
import com.wrecker.newsapp.ui.webview.NewsWebViewClient


class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    private val _viewModel: MainViewModel by viewModels()
    private val args: NewsDetailsFragmentArgs by navArgs()
    private var _binding: FragmentNewsDetailsBinding? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsDetailsBinding.bind(view)
        val article: Article = args.article
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding!!.webView.apply {
            webViewClient = NewsWebViewClient()
            loadUrl(article.url!!)
            visibility = View.GONE

        }
        _binding!!.progressBar.visibility = View.GONE
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                /**
                 * Reset the resources:
                 */

                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        class NewsWebViewClient : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                _binding!!.progressBar.visibility = View.VISIBLE
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
                _binding!!.progressBar.visibility = View.GONE
                _binding!!.webView.visibility = View.VISIBLE

            }
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                findNavController().navigate(R.id.action_newsDetailsFragment_to_errorFragment)
            }
        }
    }
}

