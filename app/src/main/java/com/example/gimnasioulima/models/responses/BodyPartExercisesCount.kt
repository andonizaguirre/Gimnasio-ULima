package com.example.gimnasioulima.models.responses

import com.google.gson.annotations.SerializedName

data class BodyPartExercisesCount (
    val exercises: Int,
    @SerializedName("body_parts")
    val bodyParts: Int,
)