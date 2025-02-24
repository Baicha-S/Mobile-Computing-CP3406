package com.example.assignment1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val calendar = R.drawable.screenshot_2025_02_16_204158

@Composable

fun AppointmentPage(padding: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Appointment for Pet 1",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = calendar),
            contentDescription = "calendar",
            modifier = Modifier
                .size(350.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        DetailsBox(title = "Appointment Details") {
            Text("Next Appointment: February XX, 2025")
            Text("Upcoming Vaccinations: Up to date")
            Text("Past Appointments: None")
        }

        Spacer(modifier = Modifier.height(16.dp))

        DetailsBox(title = "Exercise Schedule") {
            Text("Daily Walk: 30 minutes") // Replace with actual data
            Text("Playtime: 15 minutes")
            Text("Other Activities: Fetch, Tug-of-War")
        }
    }
}


@Composable
fun DetailsBox(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}