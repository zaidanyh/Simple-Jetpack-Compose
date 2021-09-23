package com.zaidan.simplejetpackcompose.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import com.zaidan.simplejetpackcompose.ui.detail.DetailActivity

@Composable
fun MainContent(
    context: Context,
    newsList: List<ArticlesItem>
) {
    LazyColumn {
        itemsIndexed(
            items = newsList
        ) { _, news ->
            ItemNews(news = news, onClick = {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.KEY_DETAIL, news)
                startActivity(context, intent, null)
            })
        }
    }
}