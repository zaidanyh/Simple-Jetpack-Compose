package com.zaidan.simplejetpackcompose.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidan.simplejetpackcompose.data.MainRepository
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import com.zaidan.simplejetpackcompose.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val _news = MutableStateFlow<List<ArticlesItem>>(ArrayList())
    val news: StateFlow<List<ArticlesItem>> get() = _news
    private val _selectedCategory = MutableStateFlow<NewsCategory?>(null)
    val selectedCategory: StateFlow<NewsCategory?> get() = _selectedCategory
    val loading = mutableStateOf(false)

    init {
        fetchNews("")
    }

    fun fetchNews(category: String?) = viewModelScope.launch {
        category?.let {
            mainRepository.getNews(it).collect { result ->
                when(result.status) {
                    Status.LOADING -> loading.value = true
                    Status.SUCCESS -> {
                        result.data?.articles?.let { list ->
                            _news.value = list
                        }
                        loading.value = false
                    }
                    Status.ERROR -> {}
                }
            }
        }
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getNewsCategory(category)
        _selectedCategory.value = newCategory
    }
}