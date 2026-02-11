package com.example.learnings.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnings.badge.BadgeVariable
import com.example.learnings.badge.IconRow

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
        IconRow(
            items = badges,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}
