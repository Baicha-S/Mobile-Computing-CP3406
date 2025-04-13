package com.example.assignment1.data

class PetRepositoryImpl(private val petDao: PetDao) : PetRepository {

    override suspend fun getPets(): List<Pet> = petDao.getAllPets()

    override suspend fun getPetById(id: Int): Pet? = petDao.getPetById(id)

    override suspend fun insertPet(pet: Pet) {
        petDao.insertPet(pet)
    }

    override suspend fun deletePet(pet: Pet) {
        petDao.deletePet(pet)
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
}
