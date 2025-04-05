package com.example.assignment1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetRepository
import kotlinx.coroutines.launch

class PetsViewModel(
    private val petsRepository: PetRepository
) : ViewModel() {

    private val _petList = MutableLiveData<List<Pet>>()
    val petList: LiveData<List<Pet>> = _petList

    init {
        loadPets()
    }

    private fun loadPets() {
        viewModelScope.launch {
            try {
                _petList.value = petsRepository.getPets()
            } catch (e: Exception) {
                // Handle error (e.g., show error message)
            }
        }
    }
}