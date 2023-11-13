package com.example.gimnasioulima.screenmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.gimnasioulima.services.UserService

class CreateUserScreenViewModel: ViewModel() {
    var name: String by mutableStateOf("")
    var surname: String by mutableStateOf("")
    var dni: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var phone: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var passwordRepeat: String by mutableStateOf("")
    var termsAndConditionsChecked: Boolean by mutableStateOf(false)

//    fun btnAccessPressed(navController: NavController): Unit {
//        if (userService.checkUser(user, password) != 0) {
//            message = "Usuario validado!"
//            navController.navigate("my_exercises")
//        } else {
//            message = "Usuario o contraseña incorrectos"
//        }
//    }
}