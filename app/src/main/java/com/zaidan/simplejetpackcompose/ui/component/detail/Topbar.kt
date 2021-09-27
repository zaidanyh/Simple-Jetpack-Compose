package com.zaidan.simplejetpackcompose.ui.component.detail

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaidan.simplejetpackcompose.R

@Composable
fun TopBarDetail(
    activity: Activity
) {
    Column {
        Surface(modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colors.primary,
            elevation = 6.dp
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { activity.finish() },
                    Modifier
                        .fillMaxWidth(.1f)
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_left),
                        contentDescription = null
                    )
                }

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
            }
        }
    }
}