package com.example.gimnasioulima.screenmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.gimnasioulima.configs.BackendClient
import com.example.gimnasioulima.services.UserService
import com.example.gimnasioulima.storages.UserStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class PasswordChangeScreenViewModel(private val context: Context): ViewModel() {

    var dni: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var termsAndConditionsChecked: Boolean by mutableStateOf(false)
    var user: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var message: String by mutableStateOf("")

    private val userService = BackendClient.buildService(UserService::class.java)
    private val coroutine: CoroutineScope = viewModelScope
    val dataStore = UserStorage(context)

    fun btnAccessPressed(navController: NavController): Unit {
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