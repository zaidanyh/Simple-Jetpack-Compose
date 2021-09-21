package com.zaidan.simplejetpackcompose.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidan.simplejetpackcompose.data.MainRepository
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    val news: MutableState<List<ArticlesItem>> = mutableStateOf(listOf())
    init {
        fetchNews()
    }

    private fun fetchNews() = viewModelScope.launch {
        mainRepository.getNews("").collect { result ->
            result.data?.articles?.let {
                news.value = it
            }
        }
    }
}