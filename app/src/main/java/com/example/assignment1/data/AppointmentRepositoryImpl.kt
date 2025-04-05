package com.example.assignment1.data

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class AppointmentRepositoryImpl : AppointmentRepository {

    private val appointments = mutableListOf<Appointment>()
    private var nextId = 1 // Track the next available ID

    init {
        // Mocking some initial appointment data
        appointments.add(
            Appointment(
                id = nextId++,
                dateTime = LocalDateTime.of(2025, 4, 15, 10, 0),
                location = "Downtown",
                description = "Checkup"
            )
        )
        appointments.add(
            Appointment(
                id = nextId++,
                dateTime = LocalDateTime.of(2025, 4, 16, 14, 30),
                location = "Downtown",
                description = "Vaccination"
            )
        )
    }

    override suspend fun getAllAppointments(): List<Appointment> = withContext(Dispatchers.IO) {
        appointments.toList() // Return a copy
    }

    override suspend fun insertAppointment(appointment: Appointment) = withContext(Dispatchers.IO) {
        val newAppointment = appointment.copy(id = nextId++) // Assign a new ID
        appointments.add(newAppointment)
    }

    override suspend fun getAppointmentsForDate(date: LocalDateTime): List<Appointment> = withContext(Dispatchers.IO) {
        appointments.filter { it.dateTime.toLocalDate() == date.toLocalDate() }
    }
}