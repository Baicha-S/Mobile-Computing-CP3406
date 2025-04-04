package com.example.assignment1.data

interface PetRepository {
    suspend fun getPetById(id: Int): Pet? // Add this function
    suspend fun getPets(): List<Pet>
}

