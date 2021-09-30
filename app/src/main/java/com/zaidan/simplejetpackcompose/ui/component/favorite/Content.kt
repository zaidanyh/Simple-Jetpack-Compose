package com.zaidan.simplejetpackcompose.ui.component.favorite

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import com.zaidan.simplejetpackcompose.ui.component.main.ItemNews
import com.zaidan.simplejetpackcompose.ui.detail.DetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalMaterialApi
@Composable
fun FavoriteContent(
    context: Context,
    listFav: List<ArticlesItem>,
    onDeleteFromDb: (ArticlesItem) -> Unit,
    onRefreshData: () -> Unit
) {
    val snackBarHostState = remember{ SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LazyColumn {
        itemsIndexed(
            items = listFav,
            key = { _, item ->
                item.hashCode()
            }
        ) { _, item ->
            val state = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart) {
                        onDeleteFromDb(item)
                        scope.launch(Dispatchers.Main) {
                            withContext(Dispatchers.IO) { onRefreshData() }
                            snackBarHostState.showSnackbar(
                                message = "Hey look a snackbar",
                                actionLabel = "Oke",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                    true
                }
            )

            SwipeToDismiss(
                state = state,
                background = {
                    val color = when(state.dismissDirection) {
                        DismissDirection.StartToEnd -> Color.Transparent
                        DismissDirection.EndToStart -> Color.Red
                        null -> Color.Transparent
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .background(color)
                            .padding(end = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                },
                dismissContent = {
                    ItemNews(
                        news = item,
                        onClick = {
                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.KEY_DETAIL, item)
                            context.startActivity(intent)
                        })
                },
                directions = setOf(DismissDirection.EndToStart)
            )
            Divider()
        }
    }
    OnActionDeleteFromDb(snackBarHostState = snackBarHostState)
}