package com.zaidan.simplejetpackcompose.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zaidan.simplejetpackcompose.ui.main.NewsCategory
import com.zaidan.simplejetpackcompose.ui.main.getAllNewsCategory

@Composable
fun MainTopBar(
    selectedCategory: NewsCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onFetchNews: (String) -> Unit
) {
    Surface(modifier = Modifier
        .fillMaxWidth(),
        color = MaterialTheme.colors.onPrimary,
        elevation = 6.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

        }

        LazyRow(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 8.dp)
        ) {
            itemsIndexed(
                items = getAllNewsCategory()
            ) { _, category ->
                NewsCategoryChip(
                    category = category.value,
                    isSelected = selectedCategory == category,
                    onExecuteChanged = {
                        onSelectedCategoryChanged(it)
                        onFetchNews(it)
                    }
                )
            }
        }
    }
}