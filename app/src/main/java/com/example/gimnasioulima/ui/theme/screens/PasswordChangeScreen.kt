package com.example.gimnasioulima.ui.theme.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.example.gimnasioulima.R
import com.example.gimnasioulima.ui.theme.Black00
import com.example.gimnasioulima.ui.theme.Black20
import com.example.gimnasioulima.ui.theme.Black40
import com.example.gimnasioulima.ui.theme.Gray200
import com.example.gimnasioulima.ui.theme.Gray800
import com.example.gimnasioulima.ui.theme.Orange400
import com.example.gimnasioulima.ui.theme.White400

@Composable
fun ButtonWithIcon2(
    text: String,
    shape: Shape,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Black00, // Text and icon color
            containerColor = Orange400 // Button background color
        ),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
        shape = shape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Icon(
            //imageVector = icon,
            //contentDescription = null,
            //modifier = Modifier.size(32.dp)
            //)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLeadingIcon2(  // creación de linea de texto junto a un icono
    leadingIcon: ImageVector,
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit
) {
    val colorFormLogin: Color = if (isSystemInDarkTheme ()) Black40 else White400

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            //border(1.dp, borderColor)
            .padding(5.dp)
            .background(color = Color.Transparent)
        ,
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text(text = placeholder, fontSize = 16.sp)
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorFormLogin,
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray,
            placeholderColor = Color.Gray
        ),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = (if (isSystemInDarkTheme()) Orange400 else Black00),
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
                    .clickable { /* Handle icon click if needed */ }
            )
        },
    )
}

@Composable
fun TopScreen2(){
    val colorBackground: Color = if (isSystemInDarkTheme ()) Black00 else Gray200  // creación de variables para colores tanto en modo claro y oscuro
    val colorLabel: Color = if (isSystemInDarkTheme ()) White400 else Black00
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorBackground)
                .weight(3f)
                .padding(8.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            val paddingPercentage = 50
            val paddingValue = with(LocalDensity.current) {
                (paddingPercentage * 0.02f * 10.dp.toPx()).dp
            }
            Column(
                modifier = Modifier.padding(top = paddingValue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start, // Align content to the start (left)
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // "ArrowBack" icon for visual effect in the top-left corner
                    IconButton(
                        onClick = { /* No action needed, as per your requirement */ },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_ulima),
                    contentDescription = "Universidad de Lima",
                    modifier = Modifier.size(120.dp),
                    colorFilter = ColorFilter.tint(Orange400),
                )
                Text(
                    text = "Gimnasio ULima",
                    textAlign = TextAlign.Center,
                    color = colorLabel,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 40.sp,
                        fontFamily = FontFamily(Font(R.font.calibrib)),
                        color = if (isSystemInDarkTheme()) Black00 else Orange400
                    )
                )
            }
        }
    }
}

@Composable
fun LoginForm2(screenWidthDp: Int, screenHeightDp: Int){
    val colorBackgroundLogin: Color = if (isSystemInDarkTheme ()) Black00 else White400
    val colorBackgroundForm: Color = if (isSystemInDarkTheme ()) Black20 else White400
    val colorThicknessForm : Color = if (isSystemInDarkTheme()) Black40 else Black40
    Box( // caja gris (light)
        modifier = Modifier
            .fillMaxSize()
            .padding(top = (screenHeightDp * 0.35).dp)
            .background(colorBackgroundLogin),
    ) {
        Box(
            modifier = Modifier.padding(
                start = (screenWidthDp * 0.150).dp,
                top = 50.dp
            ),
        ){
            Box(
                modifier = Modifier
                    .size(
                        (screenWidthDp * 0.70).dp,
                        (screenHeightDp * 0.40).dp
                    ) // Adjust the size as needed
                    .border(1.dp, colorThicknessForm)
                    .background(colorBackgroundForm)
                    .shadow(
                        elevation = 0.dp,
                        shape = MaterialTheme.shapes.medium,
                        //color = Color.Gray
                    )
                    .padding(start = 20.dp, top = 25.dp, bottom = 20.dp, end = 20.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text ="SOLICITE CAMBIO DE CONTRASEÑA", fontSize = 13.sp)
                    TextFieldWithLeadingIcon2(
                        leadingIcon = Icons.Default.AccountBox, // Replace with your desired icon
                        placeholder = "DNI",
                        text = "",
                        onTextChanged = {
                            println(it)
                        }
                    )
                    TextFieldWithLeadingIcon2(
                        leadingIcon = Icons.Default.Email, // Replace with your desired icon
                        placeholder = "Correo",
                        text = "",
                        onTextChanged = {
                            println(it)
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        horizontalArrangement = Arrangement.Center,
                    ){
                        ButtonWithIcon2("ENVIAR CORREO", RectangleShape) {}
                    }
                }
            }
        }
    }
}

@Composable
fun GoToReset2(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Row {
            Text(text = "", textAlign = TextAlign.End, color = Gray800, fontSize = 16.sp)
            Text(text = "", textAlign = TextAlign.End, color = Orange400, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PasswordChangeScreen() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    TopScreen2()
    LoginForm2(screenWidthDp, screenHeightDp)
    GoToReset2()
}
