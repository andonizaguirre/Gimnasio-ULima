package com.example.gimnasioulima.models

import com.google.gson.annotations.SerializedName

//import com.google.gson.annotations.SerializedName

data class Exercise (
    val id: Int,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("video_url")
    val videoUrl: String,
    val description: String,
    val bodyPartId: Int,
)