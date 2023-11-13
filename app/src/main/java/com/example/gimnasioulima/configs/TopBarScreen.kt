package com.example.gimnasioulima.configs

data class TopBarScreen (
    val route: String,
    val title: String,
    val onclick: (() -> Unit)? = null
)