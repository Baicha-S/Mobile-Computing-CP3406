package com.example.assignment1

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

val boxColor = Color(0xFFD1BEA8) // #d1bea8 - Define this globally
@Composable
fun AddPetProfilePage(padding: Modifier, navController: NavHostController) {
    var petName by remember { mutableStateOf("") }
    var petAge by remember { mutableStateOf("") }
    var petBreed by remember { mutableStateOf("") }
    var petAllergies by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Top, // Align to top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add New Pet Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = petName,
            onValueChange = { petName = it },
            label = { Text("Pet Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = petAge,
            onValueChange = { petAge = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = petBreed,
            onValueChange = { petBreed = it },
            label = { Text("Breed") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = petAllergies,
            onValueChange = { petAllergies = it },
            label = { Text("Allergies") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Save pet profile logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = boxColor, // Use BoxColor for the button background
                contentColor = contentColorFor(boxColor) // Automatic content color
            )
        ) {
            Text(text = "Save")
        }
    }
}
