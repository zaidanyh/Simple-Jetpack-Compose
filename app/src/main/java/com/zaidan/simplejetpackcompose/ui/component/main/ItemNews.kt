package com.zaidan.simplejetpackcompose.ui.component.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaidan.simplejetpackcompose.R
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import com.zaidan.simplejetpackcompose.utils.loadPicture

const val DEFAULT_NEWS_IMAGE = R.drawable.ic_baseline_image_not_supported

@Composable
fun ItemNews(
    news: ArticlesItem,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 12.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(.25f)
                .wrapContentWidth(Alignment.Start)
            ) {
                val image = news.urlToImage?.let { loadPicture(url = it, defaultImage = DEFAULT_NEWS_IMAGE).value }
                image?.let { url ->
                    Image(
                        bitmap = url.asImageBitmap(),
                        contentDescription = R.string.news_image.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .padding(horizontal = 6.dp, vertical = 4.dp)
            ) {
                Text(
                    text = news.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                news.description?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start)
                            .padding(top = 2.dp),
                        fontSize = 14.sp,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}