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
import com.example.assignment1.viewModel.ExerciseViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.assignment1.data.Pet

@Composable
fun ExercisePage(padding: Modifier, navController: NavHostController) {
    val viewModel: ExerciseViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.loadAllPetsExerciseData()
    }

    val petExercises by viewModel.allPetExerciseData.observeAsState(emptyList())

    LazyColumn(
        modifier = padding
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Pet Exercise Tracker",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(petExercises) { exercise ->
            PetExerciseItem(exercise = exercise, onGoalChange = { hours -> viewModel.setExerciseGoal(exercise.id, hours) }, onProgressAdd = { hours -> viewModel.addExerciseProgress(exercise.id, hours)})
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        }
    }
}

@Composable
fun PetExerciseItem(exercise: Pet, onGoalChange: (Float) -> Unit, onProgressAdd: (Float) -> Unit) {
    var goalHours by remember { mutableFloatStateOf(exercise.exerciseGoalHours) }
    var addHours by remember { mutableFloatStateOf(0f) }
    val progress = remember(exercise.exerciseProgressHours, exercise.exerciseGoalHours) {
        if (exercise.exerciseGoalHours > 0) {
            exercise.exerciseProgressHours / exercise.exerciseGoalHours
        } else {
            0f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Pet: ${exercise.name}", fontWeight = FontWeight.Bold)
        Text(text = "Hours Walked (Weekly): ${exercise.exerciseProgressHours} / ${exercise.exerciseGoalHours} hours")

        // Progression Bar
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(20.dp) // Increase the height
                .padding(vertical = 12.dp) //Adjust vertical padding,
        )

        OutlinedTextField(
            value = goalHours.toString(),
            onValueChange = { goalHours = it.toFloatOrNull() ?: exercise.exerciseGoalHours },
            label = { Text("Set Goal (hours)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = { onGoalChange(goalHours) }) {
            Text("Set Goal")
        }

        OutlinedTextField(
            value = addHours.toString(),
            onValueChange = { addHours = it.toFloatOrNull() ?: 0f },
            label = { Text("Add Hours") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = { onProgressAdd(addHours) }) {
            Text("Add Progress")
        }
    }
}
