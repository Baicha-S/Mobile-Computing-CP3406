package com.example.assignment1.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.assignment1.R
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PetRepositoryImpl : PetRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    private val pets: MutableList<Pet> = mutableListOf(
        Pet(1, "Bella", "Pomeranian", R.drawable.screenshot_2025_02_16_191409, LocalDate.parse("2019-02-20", DateTimeFormatter.ISO_DATE),"Male", "Egg"),
        Pet(3, "Charlie", "Pomeranian", R.drawable.screenshot_2025_02_16_191358, LocalDate.parse("2019-02-20", DateTimeFormatter.ISO_DATE),"Male", "Egg"),
        Pet(5, "Cooper", "Pomeranian", R.drawable.screenshot_2025_02_16_191358, LocalDate.parse("2019-02-20", DateTimeFormatter.ISO_DATE),"Male", "Egg")
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPets(): List<Pet> {
        delay(1000) //Simulate network delay
        return pets
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPetById(id: Int): Pet? {
        delay(1000) //Simulate network delay
        return pets.find { it.id == id }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun setExerciseGoal(petId: Int, hours: Float) {
        delay(1000) //Simulate network delay
        val pet = pets.find { it.id == petId }
        pet?.exerciseGoalHours = hours
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun addExerciseProgress(petId: Int, hours: Float) {
        delay(1000) // Simulate network delay
        val pet = pets.find { it.id == petId }
        if (pet != null) {
            pet.exerciseProgressHours += hours
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPetExerciseData(petId: Int): Pet? {
        delay(1000) //Simulate network delay
        return pets.find { it.id == petId }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllPetsExerciseData(): List<Pet> {
        delay(1000) //Simulate network delay
        return pets
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun addMedicalHistory(petId: Int, history: String) {
        delay(1000) //Simulate network delay
        val pet = pets.find { it.id == petId }
        pet?.medicalHistory?.add(history)
    }
}
