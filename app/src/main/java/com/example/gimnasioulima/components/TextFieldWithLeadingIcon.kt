package com.example.gimnasioulima.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gimnasioulima.ui.theme.Orange40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLeadingIcon(  // creación de linea de texto junto a un icono
    leadingIcon: ImageVector,
    placeholder: String,
    text: String,
    isPassword: Boolean? = false,
    onTextChanged: (String) -> Unit
) {
    val containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text(text = placeholder, fontSize = 14.sp)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = containerColor,
            textColor = contentColor,
            placeholderColor = Color.Gray
        ),
        visualTransformation = if (isPassword == false) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = Orange40,
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
                    .clickable { /* Handle icon click if needed */ }
            )
        }
    )
}