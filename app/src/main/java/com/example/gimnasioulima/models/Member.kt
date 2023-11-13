package com.example.gimnasioulima.models

data class Member (
    val id: Int,
    val code: Int,
    val dni: String,
    val names: String,
    val lastNames: String,
    val email: String,
    val phone: String,
    val imageUrl: String,
    val levelId: Int
)