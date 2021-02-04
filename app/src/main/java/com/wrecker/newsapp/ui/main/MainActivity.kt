package com.wrecker.newsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wrecker.newsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}