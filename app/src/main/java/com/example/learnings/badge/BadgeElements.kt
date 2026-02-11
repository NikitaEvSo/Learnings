package com.example.learnings.badge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddingButton(item: BadgeVariable) {
    IconButton( onClick = {
        item.increment()
    }) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Add To ",
        )
    }
}
@Composable
fun IconRow(items: List<BadgeVariable>,modifier: Modifier = Modifier,contentDescription: String = "Home") {
    Row(
        modifier = modifier
            .padding(1.dp)
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(percent = 80)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        content = {
            for (item in items) {
                BadgedButton(item = item, contentDescription = contentDescription)
            }
        }
    )
}
@Composable
fun IconColumn(items: List<BadgeVariable>,modifier: Modifier = Modifier,contentDescription: String = "Home") {
    Column(
        modifier = modifier
            .padding(1.dp)
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(percent = 80)
            ),

        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            for (item in items) {
                BadgedButton(item = item, contentDescription = contentDescription)
            }
        }
    )
}

@Composable
fun BadgedButton(item: BadgeVariable,contentDescription: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        BadgedBox(
            badge = {
                if (item.getBadgeCounter() > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text("${item.getBadgeCounter()}")
                    }
                }
            }
        ) {
            IconButton(
                onClick = {item.reset()}
            ) {
                Icon(
                    imageVector = item.getBadgeIcon(),
                    contentDescription = contentDescription,
                )
            }
        }
    }
}
