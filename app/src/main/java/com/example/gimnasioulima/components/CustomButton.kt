package com.example.gimnasioulima.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import com.example.gimnasioulima.ui.theme.Orange40

@Composable
fun CustomButton(  //creación de un boton custom
    text: String,
    shape: Shape,
    onClick: () -> Unit
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange40, // Button background color
            contentColor = contentColor
        ),
        shape = shape
    ) {
        Text(
            text = text, fontWeight = FontWeight.Bold
        )
    }
}