package com.example.assignment1.viewModel

import com.example.assignment1.data.Pet
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.PetRepository
import kotlinx.coroutines.launch

class PetDetailsViewModel(private val repository: PetRepository) : ViewModel() {
    private val _petDetails = MutableLiveData<Pet?>()
    val petDetails: LiveData<Pet?> = _petDetails

    fun loadPetDetails(petId: Int) {
        // Now this can be safely called from the UI
        viewModelScope.launch {
            val pet = repository.getPetById(petId)
            _petDetails.value = pet
        }
    }
}
