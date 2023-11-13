package com.example.gimnasioulima.screenmodels

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gimnasioulima.configs.BackendClient
import com.example.gimnasioulima.models.Exercise
import com.example.gimnasioulima.services.ExerciseService
import com.example.gimnasioulima.services.UserService
import com.example.gimnasioulima.storages.UserStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class LoginScreenViewModel(private val context: Context) : ViewModel() {
    /*
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
    */

    var user: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var message: String by mutableStateOf("")
    var bottomSheetCollapse: Boolean by mutableStateOf(true)
    var termsAndConditionsChecked: Boolean by mutableStateOf(false)

    private val userService = BackendClient.buildService(UserService::class.java)
    private val coroutine: CoroutineScope = viewModelScope
    val dataStore = UserStorage(context)

    fun access(navController: NavController): Unit {
        println("BTN PRESSED")
        println(user)
        println(password)
        println("BTN PRESSED")
        println(user)
        println(password)

        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = userService.findOne(user, password)?.execute()
                    if (response != null) {
                        if (response.body()!!.success) {
                            val responseData = response.body()!!
                            val jsonData = JSONObject(responseData.data)
                            val userId = jsonData.getInt("user_id")
                            val memberId = jsonData.getInt("member_id")
                            // localstorage
                            dataStore.saveUserId(userId)
                            println("routine?user_id=${userId}&member_id=${memberId}")
                            launch(Dispatchers.Main) {
                                navController.navigate("routine?user_id=${userId}&member_id=${memberId}")
                            }
                        } else {
                            // Maneja errores
                            message = response.body()!!.message
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }

    }
}