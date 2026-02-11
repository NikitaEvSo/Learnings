package com.example.learnings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.learnings.badge.AddingToCartButton
import com.example.learnings.badge.BadgeVariable
import com.example.learnings.room.CartDao
import com.example.learnings.room.ProductDao
import com.example.learnings.room.ProductEntity


@Composable
fun ShopScreen(
    products: List<ProductEntity>,
    onAddToCart: suspend (productId: Int) -> Unit,
    badge: BadgeVariable
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 80.dp)
    ) {
        items(products) { product ->
            ShopItemCard(
                product = product,
                badge = badge,
                onAddToCart = onAddToCart
            )
        }
    }
}



@Composable
fun ShopItemCard(
    product: ProductEntity,
    badge: BadgeVariable,
    onAddToCart: suspend (productId: Int) -> Unit
) {
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
                    .weight(1f)
            ) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = product.description,
                    fontSize = 12.sp
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 2.dp),
                color = Color.Black,
                thickness = 1.dp
            )

            AddingToCartButton(
                item = badge,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .background(Color(57, 232, 67, 255)),
                onClickAction = { onAddToCart(product.id) }
            )
        }
    }
}
