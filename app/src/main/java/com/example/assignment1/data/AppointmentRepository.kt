package com.example.assignment1.data

import java.time.LocalDateTime

interface AppointmentRepository {
    suspend fun getAllAppointments(): List<Appointment>
    suspend fun insertAppointment(appointment: Appointment): Boolean
    suspend fun getAppointmentsForDate(date: LocalDateTime): List<Appointment>
}
