package com.zaidan.simplejetpackcompose.ui.favorit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import com.zaidan.simplejetpackcompose.ui.component.detail.TopBarDetail
import com.zaidan.simplejetpackcompose.ui.component.main.MainContent
import com.zaidan.simplejetpackcompose.ui.theme.SimpleJetpackComposeTheme
import com.zaidan.simplejetpackcompose.utils.ShimmerAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : ComponentActivity() {

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val favList = viewModel.fetchFav.value
            val isLoading = viewModel.loading.value

            SimpleJetpackComposeTheme(
                darkTheme = false
            ) {
                Column {
                    TopBarDetail(activity = this@FavoriteActivity)
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (!isLoading) MainContent(context = this@FavoriteActivity, newsList  = favList)
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