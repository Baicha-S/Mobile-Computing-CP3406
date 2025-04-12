package com.example.assignment1.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import java.time.format.DateTimeParseException

import java.util.Calendar

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddAppointmentPage(navController: NavHostController) {
    val viewModel: AppointmentViewModel = koinViewModel()

    var eventName by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    // Error messages
    var eventNameError by remember { mutableStateOf<String?>(null) }
    var locationError by remember { mutableStateOf<String?>(null) }
    var dateError by remember { mutableStateOf<String?>(null) }
    var timeError by remember { mutableStateOf<String?>(null) }

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
            selectedDate = "$year-${monthOfYear + 1}-$dayOfMonth"
        },
        year, month, day
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minuteOfHour ->
            selectedTime = String.format("%02d:%02d", hourOfDay, minuteOfHour)
        },
        hour, minute, true
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

        OutlinedTextField(
            value = eventName,
            onValueChange = { eventName = it },
            label = { Text("Event Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = eventNameError != null,
            supportingText = { if (eventNameError != null) Text(eventNameError!!) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth(),
            isError = locationError != null,
            supportingText = { if (locationError != null) Text(locationError!!) }
        )

        Spacer(modifier = Modifier.height(8.dp))

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
                    modifier = Modifier.clickable { datePickerDialog.show() }
                )
            },
            isError = dateError != null,
            supportingText = { if (dateError != null) Text(dateError!!) }
        )

        Spacer(modifier = Modifier.height(8.dp))

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
                    modifier = Modifier.clickable { timePickerDialog.show() }
                )
            },
            isError = timeError != null,
            supportingText = { if (timeError != null) Text(timeError!!) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                // Reset errors
                eventNameError = null
                locationError = null
                dateError = null
                timeError = null

                // Validate fields
                if (eventName.isBlank()) eventNameError = "Event name cannot be blank"
                if (location.isBlank()) locationError = "Location cannot be blank"
                if (selectedDate.isBlank()) dateError = "Date cannot be blank"
                if (selectedTime.isBlank()) timeError = "Time cannot be blank"

                // If no errors, proceed with saving
                if (eventNameError == null && locationError == null && dateError == null && timeError == null) {
                    try {
                        val dateTimeString = "$selectedDate $selectedTime"
                        // Adjusted format pattern to handle single/double digits for month and day
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                        val formatter2 = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm")
                        val formatter3 = DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm")
                        val formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm")
                        var dateTime: LocalDateTime? = null;
                        try {
                            dateTime = LocalDateTime.parse(dateTimeString, formatter)
                        } catch (e: DateTimeParseException) {
                            try {
                                dateTime = LocalDateTime.parse(dateTimeString, formatter2)
                            } catch (e: DateTimeParseException) {
                                try {
                                    dateTime = LocalDateTime.parse(dateTimeString, formatter3)
                                } catch (e: DateTimeParseException) {
                                    try {
                                        dateTime = LocalDateTime.parse(dateTimeString, formatter4)
                                    } catch (e: DateTimeParseException) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                        }

                        if (dateTime != null) {
                            val newAppointment = Appointment(
                                dateTime = dateTime,
                                description = eventName,
                                location = location
                            )

                            // insert to the database
                            viewModel.insertAppointment(newAppointment)

                            navController.popBackStack()
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Appointment", color = Color.Black)
        }
    }
}