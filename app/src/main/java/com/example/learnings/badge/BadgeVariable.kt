package com.example.learnings.badge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

class BadgeVariable(
    initialCounter: Int,
    val icon: ImageVector
) {
    var number by mutableIntStateOf(initialCounter)
        private set
    fun getBadgeCounter(): Int = number
    fun getBadgeIcon(): ImageVector = icon
    fun increment() {
        number++
    }
    fun reset() {
        number = 0
    }
}