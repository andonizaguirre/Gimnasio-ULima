package com.example.gimnasioulima.screenmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gimnasioulima.configs.BackendClient
import com.example.gimnasioulima.models.BodyPart
import com.example.gimnasioulima.models.Exercise
import com.example.gimnasioulima.models.responses.BodyPartExercisesCount
import com.example.gimnasioulima.models.responses.ExerciseSetReps
import com.example.gimnasioulima.services.MemberService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import retrofit2.Response

class RoutineScreenViewModel(): ViewModel(){
    private val memberService = BackendClient.buildService(MemberService::class.java)
    private val coroutine: CoroutineScope = viewModelScope

    var userId: Int by mutableStateOf(0)
    var memberId: Int by mutableStateOf(0)
    var bodyPartsCount: Int by mutableStateOf(0)
    var exercisesCount: Int by mutableStateOf(0)
    val bodyPartMap = mutableMapOf<Int, String>()
    val bodyPartFlow = MutableStateFlow(bodyPartMap.toMap())
    private var _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> get() = _exercises
    fun setExercises(newItems: List<Exercise>) {
        _exercises.value = newItems
    }

    fun fetchBodyPartsExercises(){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = memberService.exercisesBodyParts(memberId).execute()
                    if (response.isSuccessful) {
                        val response: BodyPartExercisesCount = response.body()!!
                        bodyPartsCount = response.bodyParts
                        exercisesCount = response.exercises
                    } else {
                        // Maneja errores
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun fetchBodyParts(){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = memberService.bodyParts(memberId).execute()
                    if (response.isSuccessful) {
                        val list: List<BodyPart> = response.body()!!
                        for(i: BodyPart in list){
                            bodyPartMap[i.id] = i.name
                        }
                    } else {
                        // Maneja errores
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun fetchExercises(bodyPartId: Int? = null){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response: Response<List<Exercise>>
                    if(bodyPartId == null || bodyPartId == 0){
                        response = memberService.exercises(memberId).execute()
                    }else{
                        response = memberService.exercises(memberId, bodyPartId).execute()
                    }
                    if (response.isSuccessful) {
                        val list: List<Exercise> = response.body()!!
                        setExercises(list)
                    } else {
                        // Maneja errores
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun fetchExercise(exerciseId: Int){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = memberService.exercise(memberId, exerciseId).execute()
                    if (response.isSuccessful) {
                        val exercise: ExerciseSetReps = response.body()!!
                        println(exercise.toString())
                    } else {
                        // Maneja errores
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }
}