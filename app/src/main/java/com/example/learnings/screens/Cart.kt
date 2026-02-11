package com.example.learnings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnings.badge.AddingButton
import com.example.learnings.badge.BadgeVariable
import com.example.learnings.room.ProductDao

@Composable
fun CartItemCard(ID: Int, dao: ProductDao, badge: BadgeVariable) {
    val product by dao.getById(ID).collectAsState(initial = null)

    LaunchedEffect(product) {
        println("DEBUG: Product for ID $ID updated to: $product")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(percent = 100)),
        shape = RoundedCornerShape(percent = 100)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f) // Takes up remaining space
            ) {
                Text(text = product?.title ?: "Loading (ID: $ID)...",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
                Text(text = product?.description ?: "Please wait...",
                    fontSize = 12.sp)
            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 2.dp),
                color = Color.Black,
                thickness = 1.dp
            )

            AddingButton(
                item = badge,
                modifier = Modifier.fillMaxHeight().width(80.dp).background(color= Color(
                    57,
                    232,
                    67,
                    255
                )
                )
            )
        }
    }
}
