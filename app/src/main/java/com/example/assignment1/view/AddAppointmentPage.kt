package com.example.assignment1.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assignment1.data.Appointment
import com.example.assignment1.viewModel.AppointmentViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddAppointmentPage(navController: NavHostController) {
    val viewModel: AppointmentViewModel = koinViewModel()

    var eventName by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") } // Add location state

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, monthOfYear, dayOfMonth ->
            selectedDate = "$year-${monthOfYear + 1}-${dayOfMonth}"
        }, year, month, day
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        }, hour, minute, true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add New Appointment", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Event Name Input
        OutlinedTextField(
            value = eventName,
            onValueChange = { eventName = it },
            label = { Text("Event Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Location Input
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date Picker
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("Date") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Date",
                    modifier = Modifier.clickable { datePickerDialog.show() })
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Time Picker
        OutlinedTextField(
            value = selectedTime,
            onValueChange = { },
            label = { Text("Time") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    Icons.Filled.AccessTime,
                    contentDescription = "Time",
                    modifier = Modifier.clickable { timePickerDialog.show() })
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (eventName.isNotBlank() && selectedDate.isNotBlank() && selectedTime.isNotBlank() && location.isNotBlank()) { // Add location check
                    try {
                        val dateTimeString = "$selectedDate $selectedTime"
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                        val dateTime = LocalDateTime.parse(dateTimeString, formatter)

                        val newAppointment = Appointment(
                            dateTime = dateTime,
                            description = eventName,
                            location = location // Add location
                        )

                        // Crucially, save the appointment to the ViewModel:
                        viewModel.addAppointment(newAppointment)

                        navController.popBackStack() // Or navigate to a specific screen
                    } catch (e: Exception) {
                        // Handle parsing error
                    }
                } else {
                    // Handle invalid input
                }
            },
            // ... (button styling)
        ) {
            Text("Save Appointment", color = Color.Black)
        }
    }
}