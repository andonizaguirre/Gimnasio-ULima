package com.example.gimnasioulima.ui.theme.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gimnasioulima.ui.theme.Orange40

@Composable
fun IconButtonView(iconImg: ImageVector, description: String) {
    IconButton(
        onClick = {

        }, // Navigate back when clicked
    ) {
        Icon(
            imageVector = iconImg,
            contentDescription = description,
            tint = Color.Gray
        )
    }
}

@Composable
fun ImageView(url: String, description: String) {
    AsyncImage(
        model = url,
        contentDescription = description,
        modifier = Modifier
            .clip(CircleShape)
            .size(100.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ButtonView(text: String) {
    Button(
        onClick = {

        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange40, // Button background color
            contentColor = Color.Black
        ),
        shape = RectangleShape
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold)
    }
}


@Composable
fun DividerView() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp), // Adjust padding as needed
        thickness = 2.dp // You can set the thickness of the divider
    )
}

@Composable
fun TopSection() {
    val imageUrl = "https://e.rpp-noticias.io/xlarge/2021/11/02/140114_1168254.jpg" // Replace with your image URL
    Row {
        ImageView(
            url = imageUrl,
            description = "Foto de Usuario"
        )
        Column(
            modifier = Modifier.padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Carlos Tevez",
                fontSize = 25.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Icono de Persona",
                    tint = (if (isSystemInDarkTheme()) Orange40 else Color.Black)
                    )
                Column {
                    Text(
                        text = "ctevez",
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Crossfitero",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun MidSection() {
    Row(
        modifier = Modifier.padding(start = 15.dp, top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            Icons.Default.Phone,
            contentDescription = "Icono de Teléfono",
            tint = (if (isSystemInDarkTheme()) Orange40 else Color.Black)
        )
        Text(
            text = "999 888 777",
            color = Color.Gray
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = Modifier.padding(start = 15.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            Icons.Default.Email,
            contentDescription = "Icono de Correo",
            tint = (if (isSystemInDarkTheme()) Orange40 else Color.Black)
            )
        Text(
            text = "20041122@aloe.ulima.edu.pe",
            color = Color.Gray
        )
    }
}

@Composable
fun BottomSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "22",
                fontSize = 30.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Ejercicios Asignados",
                textAlign = TextAlign.Center
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "4",
                fontSize = 30.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Partes del Cuerpo Entrenadas",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProfileScreen(){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButtonView(Icons.Default.ArrowBack, "Back Button")
            IconButtonView(Icons.Default.Edit, "Edit button")
        }
        Column(
            modifier = Modifier.padding(
                horizontal = 40.dp,
                vertical = 30.dp
            )
        ) {
            TopSection()
            MidSection()
            ButtonView("Actualizar Datos")
            DividerView()
            BottomSection()
            DividerView()
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.BottomEnd
            ) {
                ButtonView("Cerrar Sesión")
            }
        }
    }
}
