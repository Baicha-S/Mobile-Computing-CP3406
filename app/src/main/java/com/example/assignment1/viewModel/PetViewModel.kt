package com.example.assignment1.viewModel

import androidx.lifecycle.ViewModel
import com.example.assignment1.data.PetRepository

class PetsViewModel(
    private val petsRepository: PetRepository
): ViewModel() {
    suspend fun getPets() = petsRepository.getPets()
}

