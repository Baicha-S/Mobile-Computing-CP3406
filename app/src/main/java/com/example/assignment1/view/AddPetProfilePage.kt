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

// Global constants for styling
val boxColor = Color(0xFFD1BEA8) // com.example.assignment1.data.Pet profile form button background color

@Composable
fun AddPetProfilePage(padding: Modifier, navController: NavHostController) {
    // State for user inputs
    var petName by remember { mutableStateOf("") }
    var petDOB by remember { mutableStateOf("") }
    var petGender by remember { mutableStateOf("") }
    var petBreed by remember { mutableStateOf("") }
    var petAllergies by remember { mutableStateOf("") }

    // Column to layout the form fields
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 80.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Reusable function for text input fields
        TextFieldWithLabel(
            value = petName,
            onValueChange = { petName = it },
            label = "com.example.assignment1.data.Pet Name"
        )
        TextFieldWithLabel(
            value = petDOB,
            onValueChange = { petDOB = it },
            label = "Birthday"
        )
        TextFieldWithLabel(
            value = petGender,
            onValueChange = { petGender = it },
            label = "Gender"
        )
        TextFieldWithLabel(
            value = petBreed,
            onValueChange = { petBreed = it },
            label = "Breed"
        )
        TextFieldWithLabel(
            value = petAllergies,
            onValueChange = { petAllergies = it },
            label = "Allergies"
        )

        // Save button
        Spacer(modifier = Modifier.height(16.dp))
        SaveButton {
            // Save pet profile logic
        }
    }
}

// Reusable function for OutlinedTextField with a label
@Composable
fun TextFieldWithLabel(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
}

// Reusable Save button
@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = boxColor,
            contentColor = contentColorFor(boxColor)
        )
    ) {
        Text(text = "Save", fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}
