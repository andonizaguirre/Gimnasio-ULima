package com.example.gimnasioulima.screens

import com.example.gimnasioulima.screenmodels.ExerciseDetailScreenViewModel

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import android.net.Uri
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.VideoView
import android.widget.MediaController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseDetailScreen(navController: NavController, model: ExerciseDetailScreenViewModel) {
    // Assuming you have the video file in the 'res/raw' folder with the name "video.mp4"
    val videoUri = Uri.parse("android.resource://pe.edu.ulima.pm20232.aulavirtual/raw/video")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Title
        Text(
            text = "Press de banca",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // 2x2 Table (Without Table Composable)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("20")
                Text("Reps")
            }
            Column {
                Text("3")
                Text("Sets")
            }
        }

        // Additional Text
        Text(
            text = "El press de banca, press de pecho, fuerza en banco, fuerza acostado o press banca, es un ejercicio de peso libre que trabaja principalmente la zona superior del cuerpo.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Video Player
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                VideoView(context).apply {
                    // Set the URI of the video
                    setVideoURI(videoUri)

                    // Create a MediaController to enable controls (play, pause, seek, etc.)
                    val mediaController = MediaController(context)
                    setMediaController(mediaController)

                    // Set looping to true to play the video in a loop
                    setOnPreparedListener { mp ->
                        mp.isLooping = true
                    }

                    // Start playing the video
                    start()
                }
            }
        )
    }
}


