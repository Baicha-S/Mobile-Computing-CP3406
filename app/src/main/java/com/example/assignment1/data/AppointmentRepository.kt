package com.example.assignment1.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface AppointmentRepository {
    suspend fun getAllAppointments(): Flow<List<Appointment>>
    suspend fun insertAppointment(appointment: Appointment): Long
    suspend fun getAppointmentsForDate(date: LocalDateTime): List<Appointment>
    suspend fun deleteAppointment(appointment: Appointment)
}

