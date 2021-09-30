package com.zaidan.simplejetpackcompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import com.zaidan.simplejetpackcompose.ui.component.main.MainContent
import com.zaidan.simplejetpackcompose.ui.component.main.MainTopBar
import com.zaidan.simplejetpackcompose.ui.theme.SimpleJetpackComposeTheme
import com.zaidan.simplejetpackcompose.utils.ShimmerAnimation
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val newsList = viewModel.news.value
            val selectedCategory = viewModel.selectedCategory.value
            val isLoading = viewModel.loading.value

            SimpleJetpackComposeTheme(
                darkTheme = false,
            ) {
                Column {
                    MainTopBar(
                        this@MainActivity,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                        onFetchNews = viewModel::fetchNews
                    )

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (!isLoading) MainContent(this@MainActivity, newsList = newsList)
                        else LazyColumn {
                            repeat(7) {
                                item { ShimmerAnimation() }
                            }
                        }
                    }
                }
            }
        }
    }
}