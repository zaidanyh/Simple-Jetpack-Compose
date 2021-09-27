package com.zaidan.simplejetpackcompose.ui.favorit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidan.simplejetpackcompose.data.MainRepository
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    val favNews: MutableState<List<ArticlesItem>> = mutableStateOf(ArrayList())
    val loading = mutableStateOf(false)

    init {
        fetchFavorite()
    }

    private fun fetchFavorite() = viewModelScope.launch {
        loading.value = true
        delay(1500)
        mainRepository.favNews().collect {
            favNews.value = it
            loading.value = false
        }
    }
}