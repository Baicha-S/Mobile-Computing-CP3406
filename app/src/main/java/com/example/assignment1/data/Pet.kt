package com.example.assignment1.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

data class Pet(
    val id: Int,
    val name: String,
    val species: String,
    val imageResId: Int,
    val petDOB: String,
    val gender: String,
    val allergies: String
)
@RequiresApi(Build.VERSION_CODES.O)

fun calculatePetAge(dob: String): String {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Adjust format if needed

    val birthDate = LocalDate.parse(dob, formatter)

    val currentDate = LocalDate.now()



    val period = Period.between(birthDate, currentDate)



    val years = period.years

    val months = period.months

    val days = period.days



    return when {

        years > 0 -> "$years years, $months months"

        months > 0 -> "$months months, $days days"

        else -> "$days days"

    }

}

