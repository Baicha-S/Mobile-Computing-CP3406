package com.example.assignment1.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.Period


@Entity(tableName = "pets")
data class Pet(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
val name: String,
val petDOB: LocalDate,
val gender: String,
val species: String,
val allergies: String,
val imageResId: Int,


    // Medical Tracker
    //val medicalHistory: MutableList<String> = mutableListOf(),

    // Exercise Tracker
    var exerciseGoalHours: Float = 3.5f, // 3.5 by default but user can adjust
    var exerciseProgressHours: Float = 3.0f // Current progress
)

@RequiresApi(Build.VERSION_CODES.O)
fun calculatePetAge(dob: LocalDate): String {
    //Calculates pet's age based on pet's DOB
    val currentDate = LocalDate.now()
    val period = Period.between(dob, currentDate)
    val years = period.years
    val months = period.months
    val days = period.days

    return when {
        years > 0 -> "$years years, $months months"
        months > 0 -> "$months months, $days days"
        else -> "$days days"
    }
}
