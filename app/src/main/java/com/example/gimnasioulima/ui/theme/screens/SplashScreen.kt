package com.example.gimnasioulima.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gimnasioulima.R
import com.example.gimnasioulima.ui.theme.Grey40
import com.example.gimnasioulima.ui.theme.Orange40


@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    val color: Color = if (isSystemInDarkTheme()) Orange40 else Grey40
    Box(
        modifier = Modifier.fillMaxSize(),
        //contentAlignment = Alignment.CenterStart
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_ulima),
                modifier = Modifier.size(80.dp),
                colorFilter = ColorFilter.tint(color),
                contentDescription = "Logo de la Universidad de Lima"
            )
            Text(
                text = "Universidad de Lima",
                modifier = modifier.padding(
                    PaddingValues(top = 10.dp)
                ),
                style = TextStyle(color = color, fontSize = 24.sp),
                fontFamily = FontFamily(Font(R.font.caslon_classico_sc_regular)),
                textAlign = TextAlign.Center
            )
        }
    }
}