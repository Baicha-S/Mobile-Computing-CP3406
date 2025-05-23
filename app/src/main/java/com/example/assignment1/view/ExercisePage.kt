package com.example.assignment1.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment1.viewModel.ExerciseViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.assignment1.data.Pet
import com.example.assignment1.ui.theme.BoxColor


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExercisePage() {
    val viewModel: ExerciseViewModel = koinViewModel()
    val pets by viewModel.pets.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                text = "Pet Exercise Tracker",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        items(pets) { pet ->
            PetExerciseItem(
                pet = pet,
                onGoalChange = { hours -> viewModel.setExerciseGoal(pet.id, hours) },
                onProgressAdd = { hours -> viewModel.addExerciseProgress(pet.id, hours) },
                onResetProgress = { viewModel.resetExerciseProgress(pet.id) }
            )
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        }
    }
}

@Composable
fun PetExerciseItem(
    pet: Pet,
    onGoalChange: (Float) -> Unit,
    onProgressAdd: (Float) -> Unit,
    onResetProgress: () -> Unit
) {
    var goalHours by remember { mutableFloatStateOf(pet.exerciseGoalHours) }
    var addHours by remember { mutableFloatStateOf(0f) }

    val progress = remember(pet.exerciseProgressHours, pet.exerciseGoalHours) {
        if (pet.exerciseGoalHours > 0) {
            val calculatedProgress = pet.exerciseProgressHours / pet.exerciseGoalHours
            Log.d(
                "PetExerciseItem",
                "Progress: $calculatedProgress, ProgressHours: ${pet.exerciseProgressHours}, GoalHours: ${pet.exerciseGoalHours}"
            )
            calculatedProgress
        } else {
            Log.d("PetExerciseItem", "GoalHours is 0, Progress: 0f")
            0f
        }
    }

    val progressColor = remember(progress) {
        when {
            progress < 0.3f -> Color.Red
            progress < 0.7f -> Color.Yellow
            else -> Color.Green
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Pet: ${pet.name}", fontWeight = FontWeight.Bold)
        Text(text = "Hours Walked (Weekly): ${pet.exerciseProgressHours} / ${pet.exerciseGoalHours} hours")

        // Bigger Progression Bar with Dynamic Color
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(30.dp),
            color = progressColor, // Set the color based on progress
        )

        OutlinedTextField(
            value = goalHours.toString(),
            onValueChange = { goalHours = it.toFloatOrNull() ?: pet.exerciseGoalHours },
            label = { Text("Set Goal (hours)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onGoalChange(goalHours) },
            colors = ButtonDefaults.buttonColors(containerColor = BoxColor)
        ) {
            Text("Set Goal", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = addHours.toString(),
            onValueChange = { addHours = it.toFloatOrNull() ?: 0f },
            label = { Text("Add Hours") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onProgressAdd(addHours) },
            colors = ButtonDefaults.buttonColors(containerColor = BoxColor)
        ) {
            Text(
                "Add Progress",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onResetProgress() },
            colors = ButtonDefaults.buttonColors(containerColor = BoxColor)
        ) {
            Text(
                "Reset Progress",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
