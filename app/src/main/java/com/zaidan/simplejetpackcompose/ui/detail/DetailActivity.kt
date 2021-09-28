package com.zaidan.simplejetpackcompose.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.ui.res.painterResource
import com.zaidan.simplejetpackcompose.R
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import com.zaidan.simplejetpackcompose.ui.component.detail.ContentDetailActivity
import com.zaidan.simplejetpackcompose.ui.component.detail.TopBarDetail
import com.zaidan.simplejetpackcompose.ui.theme.SimpleJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    companion object {
        const val KEY_DETAIL = "key_detail"
    }

    private val viewModel: DetailViewModel by viewModels()
    private var article: ArticlesItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            article = intent.getParcelableExtra(KEY_DETAIL)
            if (article != null) {

                val state = viewModel.stateFloating.value
                SimpleJetpackComposeTheme(
                    darkTheme = false
                ) {
                    Scaffold(
                        topBar = {
                            TopBarDetail(activity = this@DetailActivity)
                        },
                        bottomBar = {},
                        drawerContent = {},
                        floatingActionButton = {
                            FloatingActionButton(onClick = {},
                             backgroundColor = MaterialTheme.colors.primary) {
                                IconButton(onClick = {
                                    onChangedState()
                                    viewModel.onChangedFloatingState()
                                }) {
                                    Icon(
                                        painter = if (!state) painterResource(id = R.drawable.ic_baseline_favorite_border)
                                        else painterResource(id = R.drawable.ic_baseline_favorite), contentDescription = "",
                                        tint = MaterialTheme.colors.onPrimary
                                    )
                                }
                            }
                        }
                    ) {
                        ContentDetailActivity(
                            context = this@DetailActivity,
                            data = article!!
                        )
                    }
                }
            } else {
                finish()
            }
        }
    }

    private fun onChangedState() {
        article?.let {
            if (viewModel.stateFloating.value) {
                viewModel.deleteFromDb(it)
                Toast.makeText(this, "Artikel ini telah dihapus dari daftar favorit!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertToDb(
                    it.copy(
                        publishedAt = it.publishedAt,
                        author = it.author,
                        urlToImage = it.urlToImage,
                        description = it.description,
                        title = it.title,
                        url = it.url,
                        content = it.content
                    )
                )
                Toast.makeText(this, "Artikel ini telah ditambahkan ke daftar favorit!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}