package com.example.learnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnings.badge.BadgeVariable
import com.example.learnings.room.DatabaseProvider
import com.example.learnings.room.ProductEntity
import com.example.learnings.screens.CartItemCard
import com.example.learnings.screens.HomeScreen
import com.example.learnings.ui.theme.LearningsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningsTheme {
                val db = DatabaseProvider.get(this)
                val dao = db.productDao()

                LaunchedEffect(Unit) {
                    dao.clear()
                    dao.insert(ProductEntity(title = "Телефон", description = "Android смартфон"))
                    dao.insert(ProductEntity(title = "Планшет", description = "Большой экран"))
                    dao.insert(ProductEntity(title = "Наушники", description = "Беспроводные"))
                }

                val tHome = remember { BadgeVariable(initialCounter = 0, icon = Icons.Outlined.Home) }
                val tShopCart = remember { BadgeVariable(initialCounter = 0, icon = Icons.Outlined.ShoppingCart) }
                val pillList = listOf(tHome, tShopCart)

                val products by dao.getAll().collectAsState(initial = emptyList())

                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 32.dp, bottom = 80.dp) // Added top padding for edge-to-edge
                    ) {
                        items(products) { product ->
                            CartItemCard(
                                ID = product.id,
                                dao = dao
                            )
                        }
                    }
                    HomeScreen(badges = pillList)
                }
            }
        }
    }
}

//@Composable
//fun Start(badges: List<BadgeVariable>) {
//    Column(
//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
//            .padding(16.dp)
//    ) {
//        for (item in badges) {
//            AddingButton(item)
//        }
//    }
//}
