package com.example.learnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.learnings.badge.BadgeVariable
import com.example.learnings.room.DatabaseProvider
import com.example.learnings.room.ProductEntity
import com.example.learnings.screens.CartScreen
import com.example.learnings.screens.PillMenu
import com.example.learnings.screens.ShopScreen
import com.example.learnings.ui.theme.LearningsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningsTheme {
                // initialize data base
                val db = DatabaseProvider.get(this)
                val shopDao = db.productDao()
                val cartDao = db.cartDao()
                //setups shop data base.
                LaunchedEffect(Unit) {
                    shopDao.clear()
                    shopDao.insert(ProductEntity(title = "Телефон", description = "Android смартфон"))
                    shopDao.insert(ProductEntity(title = "Планшет", description = "Большой экран"))
                    shopDao.insert(ProductEntity(title = "Наушники", description = "Беспроводные"))
                    cartDao.clear()
                }
                // Badge variables
                val tHome =
                    remember { BadgeVariable(initialCounter = 0, icon = Icons.Outlined.Home) }
                val tShopCart = remember {
                    BadgeVariable(
                        initialCounter = 0,
                        icon = Icons.Outlined.ShoppingCart
                    )
                }
                // Make list of badges variables
                val pillList = listOf(tHome, tShopCart)
                // Getting products from shop data base
                val products by shopDao.getAll().collectAsState(initial = emptyList())
                val cartItems by cartDao.getAllCartItems().collectAsState(initial = emptyList())
                Box(modifier = Modifier.fillMaxSize()) {
                    CartScreen(
                        elements = cartItems,
                        onRemoveFromCart = { id ->
                            lifecycleScope.launch {
                                cartDao.remove(id)
                            }
                        }
                    )
                    ShopScreen(
                        products = products,
                        badge = tShopCart,
                        onAddToCart = { productId -> cartDao.addOrIncrement(productId) }
                    )
                    PillMenu(badges = pillList)
                }
            }
        }
    }
}
