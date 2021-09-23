package com.zaidan.simplejetpackcompose.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidan.simplejetpackcompose.data.MainRepository
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import com.zaidan.simplejetpackcompose.ui.main.NewsCategory
import com.zaidan.simplejetpackcompose.ui.main.getNewsCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    val news: MutableState<List<ArticlesItem>> = mutableStateOf(ArrayList())
    val selectedCategory: MutableState<NewsCategory?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    init {
        fetchNews("")
    }

    fun fetchNews(category: String?) = viewModelScope.launch {
        loading.value = true
        delay(1500)
        category?.let {
            mainRepository.getNews(it).collect { result ->
                result.data?.articles?.let { item ->
                    news.value = item
                    loading.value = false
                }
            }
        }
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getNewsCategory(category)
        selectedCategory.value = newCategory
    }
}