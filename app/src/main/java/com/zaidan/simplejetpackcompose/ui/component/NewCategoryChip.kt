package com.zaidan.simplejetpackcompose.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewsCategoryChip(
    category:String,
    isSelected: Boolean = false,
    onExecuteChanged: (String) -> Unit,
) {
    Surface(modifier = Modifier
        .padding(horizontal = 4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onExecuteChanged(category)
                }
            )
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}