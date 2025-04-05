package com.example.assignment1.data

import java.time.LocalDateTime

data class Appointment(
    val id: Int = 0, // In-memory ID generation
    val dateTime: LocalDateTime,
    val location: String,
    val description: String
)