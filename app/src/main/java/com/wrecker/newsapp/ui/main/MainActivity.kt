package com.wrecker.newsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.wrecker.newsapp.R
import com.wrecker.newsapp.databinding.ActivityMainBinding
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.ui.fragments.NewsHeadlineFragment
import com.wrecker.newsapp.ut.event.Event
import com.wrecker.newsapp.ut.event.MainStateEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(

): AppCompatActivity() {

    private val _viewModel: MainViewModel by viewModels()
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var navGraph: NavGraph


    companion object {
        final val TAG: String = MainActivity::class.java.name

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NewsApp)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main,)
        //_viewModel.setStateEvent(MainStateEvent.GetArticle)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.elevation = 0F

        val finalHost = NavHostFragment.create(R.navigation.main_graph)



        lifecycleScope.launchWhenStarted {
            //_viewModel.setStateEvent(MainStateEvent.GetArticle)

//            _viewModel.event.collect { event ->
//                when(event){
//                    is Event.Success -> {
//                        Toast.makeText(this@MainActivity, event.value.toString(),Toast.LENGTH_LONG).show()
//                        event.value.onEach {
//                           // Toast.makeText(this@MainActivity, it.description,Toast.LENGTH_LONG).show()
//
//                        }
//                    }
//                    is Event.Error -> {
//                        Toast.makeText(this@MainActivity, event.cause.toString(),Toast.LENGTH_LONG).show()
//
//                    }
//                    Event.Loading -> {
//                        binding.progressBar?.visibility = View.VISIBLE
//                    }
//                }
//            }
        }

        lifecycleScope.launchWhenStarted {
            _viewModel.eventMain.collect {
                when(it){
                    MainStateEvent.GetArticle -> {
                        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_container,finalHost)
                            .setPrimaryNavigationFragment(finalHost)
                            .commitNow()
                    }
                    MainStateEvent.None -> {
                    }
                    MainStateEvent.Error -> {
                        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_container,finalHost)
                            .setPrimaryNavigationFragment(finalHost)
                            .commitNow()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

    }

}