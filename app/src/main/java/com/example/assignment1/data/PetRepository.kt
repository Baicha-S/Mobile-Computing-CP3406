package com.example.assignment1.data

interface PetRepository {
    suspend fun getPets(): List<Pet>
    suspend fun insertPet(pet: Pet)
    suspend fun getPetById(petId: Int): Pet?
    suspend fun updatePet(pet: Pet)
    suspend fun deletePet(pet: Pet)
    suspend fun resetExerciseProgress(petId: Int)
}