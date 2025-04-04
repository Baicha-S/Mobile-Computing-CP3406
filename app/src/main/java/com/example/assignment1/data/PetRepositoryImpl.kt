package com.example.assignment1.data
import com.example.assignment1.R
import kotlinx.coroutines.delay

class PetRepositoryImpl : PetRepository {
    override suspend fun getPets(): List<Pet> { // Removed petId parameter
        delay(1000) // Simulate network delay
        return listOf(
            Pet(1, "Bella", "Pomeranian", R.drawable.screenshot_2025_02_16_191409, "2019-02-20","Male", "Egg" ),
            Pet(2, "Luna", "Pomeranian", R.drawable.screenshot_2025_02_16_191409, "2019-02-20","Male", "Egg"),
            Pet(3, "Charlie", "Pomeranian", R.drawable.screenshot_2025_02_16_191358, "2019-02-20","Male", "Egg"),
            Pet(4, "Lucy", "Pomeranian", R.drawable.screenshot_2025_02_16_191358, "2019-02-20","Male", "Egg"),
            Pet(5, "Cooper", "Pomeranian", R.drawable.screenshot_2025_02_16_191358, "2019-02-20","Male", "Egg")
        )
    }

    override suspend fun getPetById(id: Int): Pet? { // Made suspend
        return getPets().find { it.id == id }
    }
}

