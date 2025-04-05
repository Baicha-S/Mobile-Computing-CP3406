package com.example.assignment1.viewModel

import com.example.assignment1.data.Pet
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment1.data.PetRepository

class PetDetailsViewModel(private val repository: PetRepository) : ViewModel() {
    private val _petDetails = MutableLiveData<Pet?>()
    val petDetails: LiveData<Pet?> = _petDetails

    suspend fun loadPetDetails(petId: Int) { // Changed to Int petId
        val pet = repository.getPetById(petId) // Assumes repository has getPetById
        _petDetails.value = pet
    }
}
