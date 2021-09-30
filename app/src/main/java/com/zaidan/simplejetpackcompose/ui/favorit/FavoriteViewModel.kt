package com.zaidan.simplejetpackcompose.ui.favorit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidan.simplejetpackcompose.data.MainRepository
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val _fetchFav = MutableStateFlow<List<ArticlesItem>>(ArrayList())
    val fetchFav: StateFlow<List<ArticlesItem>> get() = _fetchFav
    val loading = mutableStateOf(false)

    init {
        fetchFavorite()
    }

    fun fetchFavorite() = viewModelScope.launch {
        loading.value = true
        mainRepository.favNews().collect {
            _fetchFav.value = it
            loading.value = false
        }
    }

    fun deleteFromDb(articlesItem: ArticlesItem) = viewModelScope.launch {
        mainRepository.deleteNews(articlesItem
        )
    }
}