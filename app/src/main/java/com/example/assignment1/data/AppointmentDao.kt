package com.example.assignment1.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: Appointment): Long

    @Query("SELECT * FROM appointments")
    fun getAllAppointments(): Flow<List<Appointment>>

    @TypeConverters(Converters::class)
    @Query("SELECT * FROM appointments WHERE dateTime BETWEEN :start AND :end")
    suspend fun getAppointmentsForDate(start: LocalDateTime, end: LocalDateTime): List<Appointment>

    @Delete
    suspend fun deleteAppointment(appointment: Appointment)
}