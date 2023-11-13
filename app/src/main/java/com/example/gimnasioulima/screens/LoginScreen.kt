package com.example.gimnasioulima.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gimnasioulima.components.Background
import com.example.gimnasioulima.components.CustomButton
import com.example.gimnasioulima.components.Header
import com.example.gimnasioulima.components.TextFieldWithLeadingIcon
import com.example.gimnasioulima.screenmodels.LoginScreenViewModel
import com.example.gimnasioulima.ui.theme.Orange40

@Composable
fun LoginForm(
    navController: NavController,
    loginScreenViewModel: LoginScreenViewModel,
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "INGRESA ESTA INFORMACIÓN",
            fontSize = 14.sp,
            color = contentColor
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Person, // Replace with your desired icon
            placeholder = "Usuario",
            text = loginScreenViewModel.user,
            onTextChanged = {
                loginScreenViewModel.user = it
            }
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Lock,// Replace with your desired icon
            text = loginScreenViewModel.password,
            placeholder = "Contraseña",
            onTextChanged = {
                loginScreenViewModel.password = it
            },
            isPassword = true
        )
        CustomButton("LOGIN", RectangleShape) {
            loginScreenViewModel.btnAccessPressed(navController)
        }
        if(loginScreenViewModel.message != "") {
            Text(loginScreenViewModel.message)
        } else {
            Text("", color = contentColor)
        }
        Row() {
            Text(
                text = "No tienes una cuenta? ",
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                color = contentColor
            )
            Text(
                text = "Créala aquí",
                textAlign = TextAlign.End,
                color = Orange40,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate("create_user")
                }
            )
        }
    }
}


@Composable
fun FrontLogin(
    screenWidthDp: Int,
    screenHeightDp: Int,
    navController: NavController,
    loginScreenViewModel: LoginScreenViewModel,
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
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
            LoginForm(navController, loginScreenViewModel)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Row() {
            Text(text = "Olvidó su contraseña? ",
                textAlign = TextAlign.End,
                color = contentColor,
                fontSize = 12.sp
            )
            Text(
                text = "Recupérala Aquí",
                textAlign = TextAlign.End,
                color = Orange40,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    println("Cambiar Contraseña")
                    navController.navigate("password_change")
                },
            )
        }
    }
}

@Composable
fun LoginScreen(navController: NavController, loginScreenViewModel: LoginScreenViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    Background()
    FrontLogin(screenWidthDp, screenHeightDp, navController, loginScreenViewModel)
}
