package com.wrecker.newsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.wrecker.newsapp.R
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.ut.event.Event
import com.wrecker.newsapp.ut.event.MainStateEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val _viewModel: MainViewModel by viewModels()

    companion object {
        final val TAG: String = MainActivity::class.java.name

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        _viewModel.setStateEvent(MainStateEvent.GetArticle)

        lifecycleScope.launchWhenStarted {
            //_viewModel.setStateEvent(MainStateEvent.GetArticle)

            _viewModel.event.collect { event ->
                Toast.makeText(this@MainActivity, event.toString(),Toast.LENGTH_LONG).show()
                when(event){
                    is Event.Success -> {
                        event.value.onEach {
                            Toast.makeText(this@MainActivity, it.description,Toast.LENGTH_LONG).show()

                        }
                    }
                    is Event.Error -> {
                        Toast.makeText(this@MainActivity, "error",Toast.LENGTH_LONG).show()

                    }
                    Event.Loading -> {
                        Toast.makeText(this@MainActivity, "Loading",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()

    }

}