package com.example.assignment1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // In-memory ID generation
    val dateTime: LocalDateTime,
    val location: String,
    val description: String
)