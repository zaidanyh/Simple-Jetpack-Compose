package com.zaidan.simplejetpackcompose.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidan.simplejetpackcompose.data.MainRepository
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
   private val mainRepository: MainRepository
): ViewModel() {

    val stateFloating = mutableStateOf(false)

    fun onChangedFloatingState() {
        stateFloating.value = !stateFloating.value
    }

    fun insertToDb(articlesItem: ArticlesItem) = viewModelScope.launch {
        mainRepository.insertNews(articlesItem)
    }

    fun deleteFromDb(articlesItem: ArticlesItem) = viewModelScope.launch {
        mainRepository.deleteNews(articlesItem
        )
    }
}