package com.example.assignment1.data

data class Pet(
    val id: Int,
    val name: String,
    val species: String,
    val imageResId: Int,
    val petDOB: String, // Use for pet's age calculation
    val gender: String,
    val allergies: String
)
