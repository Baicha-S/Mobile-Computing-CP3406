package com.example.assignment1.data

interface PetRepository {
    suspend fun getPets(): List<Pet>
    suspend fun getPetById(id: Int): Pet?
    suspend fun insertPet(pet: Pet) // <-- insert method
    suspend fun deletePet(pet: Pet) // <-- delete method
    //suspend fun setExerciseGoal(petId: Int, hours: Float)
    //suspend fun addExerciseProgress(petId: Int, hours: Float)
    //suspend fun getPetExerciseData(petId: Int): Pet?
    //suspend fun getAllPetsExerciseData(): List<Pet>
    //suspend fun addMedicalHistory(petId: Int, history: String)

}