package com.example.gimnasioulima.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.gimnasioulima.screenmodels.RoutineScreenViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoutineGrid(navController: NavController, model: RoutineScreenViewModel){
    var intValue by remember { mutableIntStateOf(0) }
    val exercises by model.exercises.collectAsState()
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    LazyVerticalGrid(
        columns = GridCells.Fixed(3) // Specify the number of columns
    ) {
        items(exercises.size) { i ->
            Column(){
                println(exercises[i].imageUrl)
                Image(
                    painter = rememberImagePainter(data = exercises[i].imageUrl),
                    contentDescription = exercises[i].name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            intValue = exercises[i].id.toInt()
                            navController.navigate("exercise/edit?exercise_id=${intValue}")
                        }
                )
                Text(
                    text = exercises[i].name,
                    color = contentColor
                )
            }
        }
    }
}

@Composable
fun SelectOptionsRoutine(model: RoutineScreenViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        Modifier.padding(bottom = 20.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Lista de Partes del Cuerpo", color = contentColor)},
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "contentDescription",
                    tint = contentColor,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = contentColor,
                textColor = contentColor
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            DropdownMenuItem(onClick = {
                model.fetchExercises(0)
                selectedText = "VER TODO"
                expanded = false
            }) {
                Text(text = "VER TODO")
            }
            for ((key, value) in model.bodyPartMap) {
                DropdownMenuItem(onClick = {
                    model.fetchExercises(key)
                    selectedText = value
                    expanded = false
                }) {
                    Text(text = value)
                }
            }
        }
    }
}

@Composable
fun RoutineScreen(viewModel: RoutineScreenViewModel, navController: NavHostController) {

    //viewModel.fetchBodyPartsExercises()
    //viewModel.fetchExercises()

    Column {
        Text("userId: ${viewModel.userId}")
        Text("memberId: ${viewModel.memberId}")
        Text("Ejercicios Asignados: ${viewModel.exercisesCount}")
        Text("Partes del Cuerpo Entrenadas: ${viewModel.bodyPartsCount}")
        SelectOptionsRoutine(viewModel)
        RoutineGrid(navController, viewModel)
    }
}
