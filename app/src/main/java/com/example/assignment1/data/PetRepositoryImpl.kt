package com.example.assignment1.data

class PetRepositoryImpl(private val petDao: PetDao) : PetRepository {
    override suspend fun getPetById(petId: Int): Pet {
        return petDao.getPetById(petId)
            ?: throw NoSuchElementException("Pet with ID $petId not found")
    }

    override suspend fun getPets(): List<Pet> {
        return petDao.getAllPets()
    }


    override suspend fun insertPet(pet: Pet) {
        petDao.insertPet(pet)
    }

    override suspend fun updatePet(pet: Pet) {
        petDao.updatePet(pet)
    }

    override suspend fun deletePet(pet: Pet) {
        petDao.deletePet(pet)
    }

    override suspend fun resetExerciseProgress(petId: Int) {
        petDao.resetExerciseProgress(petId)
    }
}
