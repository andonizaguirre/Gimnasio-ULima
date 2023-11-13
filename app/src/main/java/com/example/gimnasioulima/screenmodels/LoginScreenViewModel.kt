package com.example.gimnasioulima.screenmodels

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gimnasioulima.models.Exercise
import com.example.gimnasioulima.services.ExerciseService
import com.example.gimnasioulima.services.UserService

class LoginScreenViewModel : ViewModel() {
    var user: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    val userService: UserService = UserService()
    var message: String by mutableStateOf("")

    fun btnAccessPressed(navController: NavController): Unit {
        if (userService.checkUser(user, password) != 0) {
            message = "Usuario validado!"
            navController.navigate("my_exercises")
        } else {
            message = "Usuario o contraseña incorrectos"
        }
    }
}