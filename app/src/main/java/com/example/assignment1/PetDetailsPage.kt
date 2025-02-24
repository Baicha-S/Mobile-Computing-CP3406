package com.example.assignment1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PetDetailsPage(padding: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Top, // Align to top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Details for Pet 1", // Make name dynamic later
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        DetailsBox(title = "Appointments") {
            Text("Next Appointment: February XX, 2025")
        }

        DetailsBox(title = "General Info") {
            Text("Breed: Pomeranian") // Replace with actual data
            Text("Age: 3 years")
            Text("Weight: XX lbs")
        }

        DetailsBox(title = "Medical History") {
            Text("Vaccination History: Up to date")
            Text("Allergies: None")
        }


        DetailsBox(title = "Exercise Info") {
            Text("Daily Walks: 2")
            Text("Favorite Activity: Fetch")
        }
    }
}

@Composable
fun DetailsBox(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = BoxColor, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        content() // Display the provided content
    }
}