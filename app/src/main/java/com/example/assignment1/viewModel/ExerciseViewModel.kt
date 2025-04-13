package com.example.assignment1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel(private val petDao: PetDao) : ViewModel() {

    private val _pets = MutableStateFlow<List<Pet>>(emptyList())
    val pets: StateFlow<List<Pet>> = _pets

    init {
        loadPets()
    }

    private fun loadPets() {
        viewModelScope.launch {
            _pets.value = petDao.getAllPets()
        }
    }

    fun setExerciseGoal(petId: Int, hours: Float) {
        viewModelScope.launch {
            val pet = petDao.getPetById(petId)
            if (pet != null) {
                petDao.updatePet(pet.copy(exerciseGoalHours = hours))
                loadPets()
            }
        }
    }

    fun addExerciseProgress(petId: Int, hours: Float) {
        viewModelScope.launch {
            val pet = petDao.getPetById(petId)
            if (pet != null) {
                petDao.updatePet(pet.copy(exerciseProgressHours = pet.exerciseProgressHours + hours))
                loadPets()
            }
        }
    }

    fun resetExerciseProgress(petId: Int) {
        viewModelScope.launch {
            petDao.resetExerciseProgress(petId)
            loadPets()
        }
    }
}
