package com.example.learnings.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnings.room.ProductDao
import com.example.learnings.room.ProductEntity
import kotlinx.coroutines.flow.Flow

@Composable
fun CartItemCard(ID: Int, dao: ProductDao) {
    // ID must match an existing row in the DB
    val product by dao.getById(ID).collectAsState(initial = null)

    // Debugging: This will print to Logcat whenever the database updates
    LaunchedEffect(product) {
        println("DEBUG: Product for ID $ID updated to: $product")
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            // If this stays as "Loading...", the Flow is emitting null
            // because no record with ID $ID exists.
            Text(text = product?.title ?: "Loading (ID: $ID)...")
            Text(text = product?.description ?: "Please wait...")
        }
    }
}

fun getProductByID(
    dao: ProductDao,
    id: Int
): Flow<ProductEntity?> {
    return dao.getById(id)
}