package com.example.assignment1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "medical_info")
data class MedicalInfo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val petId: Int, // Foreign key linking to Pet
    val date: Date,
    val clinicName: String,
    val vetName: String,
    val description: String
)
