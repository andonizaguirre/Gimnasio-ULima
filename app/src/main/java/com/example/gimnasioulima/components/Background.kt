package com.example.gimnasioulima.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gimnasioulima.ui.theme.Grey80

@Composable
fun Background() {
    val containerColor = if (isSystemInDarkTheme()) Grey80 else Color.White
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .weight(3f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .background(containerColor)
        )
    }
}