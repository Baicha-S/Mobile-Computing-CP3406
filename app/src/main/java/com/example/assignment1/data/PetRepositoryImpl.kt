package com.example.assignment1.data

import kotlinx.coroutines.flow.Flow

class PetRepositoryImpl(private val petDao: PetDao) : PetRepository {
    override suspend fun getPetById(petId: Int): Pet {
        return petDao.getPetById(petId) ?: throw NoSuchElementException("Pet with ID $petId not found")
    }

    override suspend fun getPets(): List<Pet> {
        return petDao.getAllPets()
    }


    override suspend fun insertPet(pet: Pet) {
        petDao.insertPet(pet)
    }

    override suspend fun updatePet(pet: Pet) {
        petDao.updatePet(pet) // Add this implementation
    }

    override suspend fun deletePet(pet: Pet) {
        petDao.deletePet(pet)
    }

    override suspend fun resetExerciseProgress(petId: Int) {
        petDao.resetExerciseProgress(petId)
    }
}
    /*override suspend fun setExerciseGoal(petId: Int, hours: Float) {
        val pet = petDao.getPetById(petId)
        if (pet != null) {
            pet.exerciseGoalHours = hours
            petDao.updatePet(pet)
        }
    }

    override suspend fun addExerciseProgress(petId: Int, hours: Float) {
        val pet = petDao.getPetById(petId)
        if (pet != null) {
            pet.exerciseProgressHours += hours
            petDao.updatePet(pet)
        }
    }

    override suspend fun getPetExerciseData(petId: Int): Pet? = petDao.getPetById(petId)

    override suspend fun getAllPetsExerciseData(): List<Pet> = petDao.getAllPets()

    override suspend fun addMedicalHistory(petId: Int, history: String) {
        val pet = petDao.getPetById(petId)
        if (pet != null) {
            pet.medicalHistory.add(history)
            petDao.updatePet(pet)
        }
    }*/

