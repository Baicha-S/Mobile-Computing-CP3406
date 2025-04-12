package com.example.assignment1.data

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.LocalTime

class AppointmentRepositoryImpl(
    private val appointmentDao: AppointmentDao
) : AppointmentRepository {

    override suspend fun getAllAppointments(): Flow<List<Appointment>> =
        withContext(Dispatchers.IO) {
            appointmentDao.getAllAppointments()
        }

    override suspend fun insertAppointment(appointment: Appointment) = withContext(Dispatchers.IO) {
        appointmentDao.insertAppointment(appointment)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAppointmentsForDate(date: LocalDateTime): List<Appointment> =
        withContext(Dispatchers.IO) {
            val startOfDay = date.toLocalDate().atStartOfDay()
            val endOfDay = date.toLocalDate().atTime(LocalTime.MAX)
            appointmentDao.getAppointmentsForDate(startOfDay, endOfDay)
        }

    override suspend fun deleteAppointment(appointment: Appointment) = withContext(Dispatchers.IO) {
        appointmentDao.deleteAppointment(appointment)
    }
}

