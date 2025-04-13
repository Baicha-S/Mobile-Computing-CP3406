package com.example.assignment1.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetDao
import kotlinx.coroutines.launch

class HomeViewModel(private val petDao: PetDao) : ViewModel() {
    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> = _pets

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadPets()
    }

    private fun loadPets() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _pets.value = petDao.getAllPets()
                _isLoading.value = false
                _error.value = null
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = e.message
            }
        }
    }

    fun deletePet(pet: Pet) {
        viewModelScope.launch {
            try {
                petDao.deletePet(pet)
                loadPets() // Reload pets after deletion
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
