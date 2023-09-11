package com.example.gimnasioulima.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.gimnasioulima.ui.theme.Grey40
import com.example.gimnasioulima.ui.theme.Orange40

@Composable
fun IconButtonView(iconImg: ImageVector, description: String) {
    IconButton(
        onClick = {

        }, // Navigate back when clicked
    ) {
        Icon(
            imageVector = iconImg,
            contentDescription = description
        )
    }
}

@Composable
fun ImageView(url: String, height: Int, width: Int) {
    val painter = // You can apply transformations here if needed
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
                // You can apply transformations here if needed
                transformations(CircleCropTransformation())
            }).build()
        )
    Image(
        painter = painter,
        contentDescription = "Fotografía del Usuario",
        modifier = Modifier
            .size(width.dp, height.dp)
    )
}

@Composable
fun ButtonView(text: String) {
    Button(
        onClick = {

        },
        modifier = Modifier
            .fillMaxWidth(),
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
            .padding(vertical = 8.dp), // Adjust padding as needed
        thickness = 1.5.dp // You can set the thickness of the divider
    )
}

@Composable
fun ProfileScreen(){
    val imageUrl = "https://e.rpp-noticias.io/xlarge/2021/11/02/140114_1168254.jpg" // Replace with your image URL
    Box(
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButtonView(Icons.Default.ArrowBack, "Back Button")
            IconButtonView(Icons.Default.Edit, "Edit button")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp, vertical = 30.dp)
        ) {
            Row {
                ImageView(url = imageUrl, width = 100, height = 100)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Carlos Tevez",
                        fontWeight = FontWeight.Bold
                    )
                    Row() {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Icono de Persona")
                        Text(
                            text = "ctevez",
                            color = Color.Gray
                        )
                    }
                    Text(
                        text = "Crossfitero",
                        color = Color.Gray
                    )
                }
            }
            Row {
                Icon(
                    Icons.Default.Phone,
                    contentDescription = "Icono de Persona")
                Text(
                    text = "999 888 777",
                    color = Color.Gray
                )
            }
            Row {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Icono de Usuario")
                Text(
                    text = "20041122@aloe.ulima.edu.pe",
                    color = Color.Gray
                )
            }
            ButtonView("Actualizar Datos")
            DividerView()
            Row {
                Column {
                    Text(
                        text = "22",
                        fontWeight = FontWeight.Bold
                    )
                    Text("Ejercicios Asignadas")
                }
                Column {
                    Text(
                        text = "4",
                        fontWeight = FontWeight.Bold
                    )
                    Text("Partes del Cuerpo Entrenadas")
                }
            }
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
