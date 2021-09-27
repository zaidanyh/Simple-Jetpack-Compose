package com.zaidan.simplejetpackcompose.ui.component.main

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.zaidan.simplejetpackcompose.R
import com.zaidan.simplejetpackcompose.ui.favorit.FavoriteActivity
import com.zaidan.simplejetpackcompose.ui.main.NewsCategory
import com.zaidan.simplejetpackcompose.ui.main.getAllNewsCategory

@Composable
fun MainTopBar(
    context: Context,
    selectedCategory: NewsCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onFetchNews: (String) -> Unit
) {
    Column {
        Surface(modifier = Modifier
            .fillMaxWidth(),
            color = MaterialTheme.colors.primary,
            elevation = 6.dp
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .wrapContentWidth(Alignment.Start)
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )

                ConstraintLayout(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    val menu = createRef()
                    IconButton(
                        onClick = { context.startActivity(Intent(context, FavoriteActivity::class.java)) },
                        modifier = Modifier
                            .constrainAs(menu) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
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