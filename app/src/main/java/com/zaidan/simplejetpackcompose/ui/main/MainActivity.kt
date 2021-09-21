package com.zaidan.simplejetpackcompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import com.zaidan.simplejetpackcompose.ui.MainViewModel
import com.zaidan.simplejetpackcompose.ui.component.ItemNews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val newsList = viewModel.news.value
            LazyColumn {
                itemsIndexed(
                    items = newsList
                ) { _, news ->
                    ItemNews(news = news, onClick = {})
                }
            }
        }
    }
}