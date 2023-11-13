package com.example.gimnasioulima.screenmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gimnasioulima.configs.BackendClient
import com.example.gimnasioulima.models.BodyPart
import com.example.gimnasioulima.models.Exercise
import com.example.gimnasioulima.services.BodyPartService
import com.example.gimnasioulima.services.ExerciseService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel: ViewModel(){
    val bodyPartsMap = mutableMapOf<Int, String>()

//    fun getBodyParts(){
//        val bodyPartService: BodyPartService = BodyPartService()
//        var bodyPartList: ArrayList<BodyPart> = bodyPartService.bodyPartList
//        for(p: BodyPart in bodyPartList){
//            val id = p.id
//            val name = p.name
//            if(!bodyPartsMap.containsKey(id)){
//                bodyPartsMap[id] = name
//            }
//        }
//    }

    fun fetchBodyParts(){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = bodyPartService.fetchAll().execute()
                    if (response.isSuccessful) {
                        val list: List<BodyPart> = response.body()!!
                        for(b: BodyPart in list){
                            val bodyPartId = b.id
                            val bodyPartName = b.name
                            bodyPartsMap[bodyPartId] = bodyPartName
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

    private var _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> get() = _exercises
    fun setExercises(newItems: List<Exercise>) {
        _exercises.value = newItems
    }

    fun listAllExercises(){
        val service: ExerciseService = ExerciseService()
        val list = service.listAll()
        setExercises(list)
    }

    fun filterByBodyParts(bodyPartId: Int){
        val service: ExerciseService = ExerciseService()
        val list = service.exerciseListByBodyPartId(bodyPartId)
        setExercises(list)
    }

    private val bodyPartService = BackendClient.buildService(BodyPartService::class.java)
    private val coroutine: CoroutineScope = viewModelScope
}