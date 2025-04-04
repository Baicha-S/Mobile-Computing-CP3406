package com.example.assignment1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetRepository
import com.example.assignment1.data.PetRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PetRepository = PetRepositoryImpl()) : ViewModel() {
    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> = _pets

    init {
        loadPets()
    }

    private fun loadPets() {
        viewModelScope.launch {
            _pets.value = repository.getPets()
        }
    }
}
