package com.example.gimnasioulima.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
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
import com.example.gimnasioulima.screenmodels.CreateUserScreenViewModel
import com.example.gimnasioulima.screenmodels.PasswordChangeScreenViewModel
import com.example.gimnasioulima.ui.theme.Orange40

@Composable
fun CreateUserForm(
    navController: NavController,
    createUserScreenViewModel: CreateUserScreenViewModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
        Text(
            text ="CREAR CUENTA",
            fontSize = 12.sp,
            color = contentColor
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Person,
            placeholder = "Nombres",
            text = createUserScreenViewModel.name,
            onTextChanged = {
                createUserScreenViewModel.name = it
            }
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Person,
            placeholder = "Apellidos",
            text = createUserScreenViewModel.surname,
            onTextChanged = {
                createUserScreenViewModel.surname = it
            }
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.AccountBox,
            placeholder = "DNI",
            text = createUserScreenViewModel.dni,
            onTextChanged = {
                createUserScreenViewModel.dni = it
            }
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Email,
            placeholder = "Correo",
            text = createUserScreenViewModel.email,
            onTextChanged = {
                createUserScreenViewModel.email = it
            }
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Call,
            placeholder = "Teléfono",
            text = createUserScreenViewModel.phone,
            onTextChanged = {
                createUserScreenViewModel.phone = it
            }
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Star, // Replace with your desired icon
            text = createUserScreenViewModel.password,
            placeholder = "Contraseña",
            onTextChanged = {
                createUserScreenViewModel.password = it
            },
            isPassword = true,
        )
        TextFieldWithLeadingIcon(
            leadingIcon = Icons.Default.Star, // Replace with your desired icon
            text = createUserScreenViewModel.passwordRepeat,
            placeholder = "Repetir Contraseña",
            onTextChanged = {
                createUserScreenViewModel.passwordRepeat = it
            },
            isPassword = true,
        )
        CustomButton("CREAR CUENTA", RectangleShape) {

        }
    }
}


@Composable
fun CreateUserFront(
    screenWidthDp: Int,
    screenHeightDp: Int,
    navController: NavController,
    createUserScreenViewModel: CreateUserScreenViewModel,
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
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Create a Box to center it within the Column
        Box(
            modifier = Modifier
                .size( // Adjust the size as needed
                    (screenWidthDp * 0.75).dp, (screenHeightDp * 0.75).dp
                )
                .background(containerColor)
                .padding(30.dp, 20.dp)
        ) {
            CreateUserForm(navController, createUserScreenViewModel)
        }
    }
}

@Composable
fun CreateUserScreen(navController: NavController, createUserScreenViewModel: CreateUserScreenViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    Background()
    CreateUserFront(screenWidthDp, screenHeightDp, navController, createUserScreenViewModel)
}
