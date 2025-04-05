package com.example.assignment1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(private val petRepository: PetRepository) : ViewModel() {

    private val _petExerciseData = MutableLiveData<Pet?>()
    val petExerciseData: LiveData<Pet?> = _petExerciseData

    private val _allPetExerciseData = MutableLiveData<List<Pet>>()
    val allPetExerciseData : LiveData<List<Pet>> = _allPetExerciseData

    private fun loadPetExerciseData(petId: Int) {
        viewModelScope.launch {
            _petExerciseData.value = petRepository.getPetExerciseData(petId)
        }
    }

    fun setExerciseGoal(petId: Int, hours: Float) {
        viewModelScope.launch {
            petRepository.setExerciseGoal(petId, hours)
            loadPetExerciseData(petId)
        }
    }

    fun addExerciseProgress(petId: Int, hours: Float) {
        viewModelScope.launch {
            petRepository.addExerciseProgress(petId, hours)
            loadPetExerciseData(petId)
        }
    }

    fun loadAllPetsExerciseData(){
        viewModelScope.launch {
            _allPetExerciseData.value = petRepository.getAllPetsExerciseData()
        }
    }

}