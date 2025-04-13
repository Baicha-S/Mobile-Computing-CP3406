package com.example.assignment1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetRepository
import kotlinx.coroutines.launch

class EditPetViewModel(private val petRepository: PetRepository) : ViewModel() {
    private val _pet = MutableLiveData<Pet>()
    val pet: LiveData<Pet> = _pet

    fun loadPet(petId: Int) {
        viewModelScope.launch {
            _pet.value = petRepository.getPetById(petId)
        }
    }

    fun updatePet(pet: Pet) {
        viewModelScope.launch {
            petRepository.updatePet(pet)
        }
    }
}
