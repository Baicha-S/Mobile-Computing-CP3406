package com.example.assignment1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// Placeholder data for demonstration
data class PetExercise(val petName: String, val hoursWalked: Float)

@Composable
fun ExercisePage(padding: Modifier, navController: NavHostController) {
    val petExercises = remember {
        mutableStateListOf(
            PetExercise("Buddy", 2.0f),
            PetExercise("Whiskers", 4.0f),
            PetExercise("Rocky", 1.5f)
        )
    }

    Column(
        modifier = padding
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pet Exercise Tracker",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        petExercises.forEach { exercise ->
            PetExerciseItem(exercise = exercise)
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun PetExerciseItem(exercise: PetExercise) {
    val targetHours = 3.5f
    val targetReached = exercise.hoursWalked >= targetHours

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Pet: ${exercise.petName}", fontWeight = FontWeight.Bold)
        Text(text = "Hours Walked (Weekly): ${exercise.hoursWalked} hours")

        if (targetReached) {
            Text(
                text = "Target Reached!",
                color = Color.Green,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                text = "Target Not Reached! (${targetHours} hours)",
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
