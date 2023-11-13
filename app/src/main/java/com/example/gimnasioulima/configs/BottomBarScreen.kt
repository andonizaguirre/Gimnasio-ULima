package com.example.gimnasioulima.configs

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector,
    val onclick: (() -> Unit)? = null
)