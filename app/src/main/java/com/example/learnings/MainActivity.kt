package com.example.learnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnings.badge.AddingButton
import com.example.learnings.badge.BadgeVariable
import com.example.learnings.badge.IconRow
import com.example.learnings.ui.theme.LearningsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningsTheme {

                // Создаем общие BadgeVariable
                val tHome = remember { BadgeVariable(initialCounter = 0, icon = Icons.Outlined.Home) }
                val tShopCart = remember { BadgeVariable(initialCounter = 0, icon = Icons.Outlined.ShoppingCart) }
                val pillList = listOf(tHome, tShopCart)

                HomeScreen(badges = pillList)


//                        FloatingBottomBar(
//                            badges = pillList,
//                            modifier = Modifier
//                                .align(Alignment.BottomCenter)
//                                .padding(bottom = 16.dp)
//                        )
                    }
                }
            }
        }

@Composable
fun Start(badges: List<BadgeVariable>) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        for (item in badges) {
            AddingButton(item)
        }
    }
}
@Composable
fun FloatingBottomBar(
    badges: List<BadgeVariable>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(percent = 50),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp,
        shadowElevation = 8.dp
    ) {
        IconRow(items = badges)
    }
}

@Composable
fun HomeScreen(badges:List<BadgeVariable>) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingBottomBar(
            badges = badges,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}