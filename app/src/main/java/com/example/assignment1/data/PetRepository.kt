package com.example.assignment1.data

interface PetRepository {
    suspend fun getPets(): List<Pet>
    suspend fun insertPet(pet: Pet)
    suspend fun getPetById(petId: Int): Pet?
    suspend fun updatePet(pet: Pet)
    suspend fun deletePet(pet: Pet)
    suspend fun resetExerciseProgress(petId: Int)
    //suspend fun setExerciseGoal(petId: Int, hours: Float)
    //suspend fun addExerciseProgress(petId: Int, hours: Float)
    //suspend fun getPetExerciseData(petId: Int): Pet?
    //suspend fun getAllPetsExerciseData(): List<Pet>
    //suspend fun addMedicalHistory(petId: Int, history: String)

}