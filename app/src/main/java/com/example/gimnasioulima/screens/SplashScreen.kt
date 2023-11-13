package com.example.gimnasioulima.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gimnasioulima.R
import com.example.gimnasioulima.ui.theme.Orange40
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToMain: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_ulima),
                modifier = Modifier.size(80.dp),
                colorFilter = ColorFilter.tint(Orange40),
                contentDescription = "Logo de la Universidad de Lima"
            )
            Text(
                text = "Universidad de Lima",
                modifier = Modifier.padding(
                    PaddingValues(top = 10.dp)
                ),
                style = TextStyle(color = Orange40, fontSize = 24.sp),
                fontFamily = FontFamily(Font(R.font.caslon_classico_sc_regular)),
                textAlign = TextAlign.Center
            )
        }
    }
    LaunchedEffect(Unit) {
        delay(1000) // Adjust the delay duration as needed
        navigateToMain()
    }
}