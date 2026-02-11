package com.example.learnings.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnings.badge.BadgeVariable

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