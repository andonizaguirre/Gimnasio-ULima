package com.example.gimnasioulima.screenmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ExerciseDetailScreenViewModel: ViewModel() {
    var exerciseId: Int by mutableStateOf(0)
}