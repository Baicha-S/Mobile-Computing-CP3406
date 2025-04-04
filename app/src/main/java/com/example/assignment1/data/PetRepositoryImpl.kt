package com.example.assignment1.data
import com.example.assignment1.R
import kotlinx.coroutines.delay

class PetRepositoryImpl : PetRepository {
    override suspend fun getPets(): List<Pet> { // Removed petId parameter
        delay(1000) // Simulate network delay
        return listOf(
            Pet(1, "Bella", "Pomeranian", R.drawable.screenshot_2025_02_16_191409),
            Pet(2, "Luna", "Pomeranian", R.drawable.screenshot_2025_02_16_191409),
            Pet(3, "Charlie", "Pomeranian", R.drawable.screenshot_2025_02_16_191358),
            Pet(4, "Lucy", "Pomeranian", R.drawable.screenshot_2025_02_16_191358),
            Pet(5, "Cooper", "Pomeranian", R.drawable.screenshot_2025_02_16_191358)
        )
    }

    override suspend fun getPetById(id: Int): Pet? { // Made suspend
        return getPets().find { it.id == id }
    }
}
