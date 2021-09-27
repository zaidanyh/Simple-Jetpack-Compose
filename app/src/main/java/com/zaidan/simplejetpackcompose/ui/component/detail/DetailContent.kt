package com.zaidan.simplejetpackcompose.ui.component.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaidan.simplejetpackcompose.R
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import com.zaidan.simplejetpackcompose.utils.loadPicture
import java.text.SimpleDateFormat

@Composable
fun ContentDetailActivity(
    context: Context,
    data: ArticlesItem
) {
    Column {
        Text(
            text =data.title,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(top = 6.dp),
            color = MaterialTheme.colors.onPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = changeFormatTime(data.publishedAt),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(top = 8.dp),
            color = MaterialTheme.colors.onPrimary,
            fontSize = 13.sp,
            textAlign = TextAlign.End
        )

        val image = data.urlToImage?.let { loadPicture(url = it, defaultImage = DEFAULT_BUFFER_SIZE).value }
        image?.let { url ->
            Image(
                bitmap = url.asImageBitmap(),
                contentDescription = R.string.news_image.toString(),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(225.dp)
                    .padding(2.dp),
            )
        }

        data.content?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 14.sp
            )
        }

        data.description?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 14.sp
            )
        }

        Text(
            text = stringResource(id = R.string.view_more),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(data.url)
                    context.startActivity(intent)
                },
            color = MaterialTheme.colors.primaryVariant,
            fontSize = 14.sp,
        )

        Text(
            text = stringResource(id = R.string.author, "${data.author}"),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .padding(top = 18.dp),
            color = MaterialTheme.colors.onPrimary,
            fontSize = 14.sp
        )
    }
}

@SuppressLint("SimpleDateFormat")
fun changeFormatTime(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("dd MMMM yyyy - HH:mm")
    val date = dateFormat.parse(dateString)
    return formatter.format(date!!)
}