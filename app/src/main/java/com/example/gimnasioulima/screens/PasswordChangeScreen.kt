package com.example.gimnasioulima.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gimnasioulima.components.Background
import com.example.gimnasioulima.components.CustomButton
import com.example.gimnasioulima.components.Header
import com.example.gimnasioulima.components.TextFieldWithLeadingIcon
import com.example.gimnasioulima.screenmodels.PasswordChangeScreenViewModel
import com.example.gimnasioulima.ui.theme.Grey40

@Composable
fun PasswordChangeForm(
    navController: NavController,
    passwordChangeScreenViewModel: PasswordChangeScreenViewModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
        Text(text ="SOLICITE CAMBIO DE CONTRASEÑA",
            fontSize = 14.sp,
            color = contentColor
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.AccountBox,
            placeholder = "DNI",
            text = passwordChangeScreenViewModel.dni,
            onTextChanged = {
                passwordChangeScreenViewModel.dni = it
            }
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Email, // Replace with your desired icon
            text = passwordChangeScreenViewModel.email,
            placeholder = "Correo",
            onTextChanged = {
                passwordChangeScreenViewModel.email = it
            }
        )
        CustomButton("ENVIAR CORREO", RectangleShape) {

        }
    }
}


@Composable
fun PasswordChangeFront(
    screenWidthDp: Int,
    screenHeightDp: Int,
    navController: NavController,
    passwordChangeScreenViewModel: PasswordChangeScreenViewModel,
) {
    val containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
    Column(horizontalAlignment = Alignment.Start) {
        IconButton(
            onClick = { navController.navigate("login")},
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }
    Header()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Create a Box to center it within the Column
        Box(
            modifier = Modifier
                .size( // Adjust the size as needed
                    (screenWidthDp * 0.75).dp, (screenHeightDp * 0.45).dp
                )
                .background(containerColor)
                .padding(30.dp, 20.dp)
        ) {
            PasswordChangeForm(navController, passwordChangeScreenViewModel)
        }
    }
}

@Composable
fun PasswordChangeScreen(navController: NavController, passwordChangeScreenViewModel: PasswordChangeScreenViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    Background()
    PasswordChangeFront(screenWidthDp, screenHeightDp, navController, passwordChangeScreenViewModel)
}
